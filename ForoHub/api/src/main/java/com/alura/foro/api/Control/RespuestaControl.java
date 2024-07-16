package com.alura.foro.api.Control;

import com.alura.foro.api.Dto.Respuesta.*;
import com.alura.foro.api.Modelo.Estado;
import com.alura.foro.api.Modelo.Respuesta;
import com.alura.foro.api.Modelo.Topico;
import com.alura.foro.api.Modelo.Usuario;
import com.alura.foro.api.Repositorio.RespuestaRepositorio;
import com.alura.foro.api.Repositorio.TopicoRepositorio;
import com.alura.foro.api.Repositorio.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuestas")
public class RespuestaControl {

    private final RespuestaRepositorio respuestaRepositorio;
    private final TopicoRepositorio topicoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    public RespuestaControl(RespuestaRepositorio respuestaRepositorio, TopicoRepositorio topicoRepositorio, UsuarioRepositorio usuarioRepositorio) {
        this.respuestaRepositorio = respuestaRepositorio;
        this.topicoRepositorio = topicoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @PostMapping
    public ResponseEntity<RetornoRespuesta> registrar(@RequestBody RegistroRespuesta datosRegistro, UriComponentsBuilder uri) {
        Topico topico = topicoRepositorio.getReferenceById(datosRegistro.topicoId());
        if (topico.getEstado() == Estado.CERRADO) {
           return ResponseEntity.unprocessableEntity().build();
        }

        Usuario autor = usuarioRepositorio.getReferenceById(datosRegistro.autorId());
        Respuesta respuesta = respuestaRepositorio.save(new Respuesta(datosRegistro, topico, autor));
        topico.agregarRespuesta(respuesta);
        RetornoRespuesta datosRespuesta = new RetornoRespuesta(respuesta);
        URI url = uri.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<ListadoRespuesta>> listar(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepositorio.findAll(paginacion).map(ListadoRespuesta::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RetornoRespuestaOld> retornaDatos(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepositorio.getReferenceById(id);
        return ResponseEntity.ok(new RetornoRespuestaOld(respuesta));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RetornoRespuesta> actualizar(@RequestBody ActualizarRespuesta datosActualizar) {
        Respuesta respuesta = respuestaRepositorio.getReferenceById(datosActualizar.id());
        Topico topico = topicoRepositorio.getReferenceById(datosActualizar.topicoId());
        Usuario autor = usuarioRepositorio.getReferenceById(datosActualizar.autorId());
        respuesta.actualizarDatos(datosActualizar, topico, autor);
        return ResponseEntity.ok( new RetornoRespuesta(respuesta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepositorio.getReferenceById(id);
        respuestaRepositorio.delete(respuesta);
        return ResponseEntity.noContent().build();
    }
}
