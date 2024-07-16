package com.alura.foro.api.Control;

import com.alura.foro.api.Dto.usuario.*;
import com.alura.foro.api.Modelo.Usuario;
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
@RequestMapping("/usuarios")
public class ControlUsuario {

    private final UsuarioRepositorio usuarioRepositorio;

    public ControlUsuario(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @PostMapping
    public ResponseEntity<RespuestaUsuario> registrar(@RequestBody RegistroUsuario datosRegistro, UriComponentsBuilder uri) {
        Usuario usuario = usuarioRepositorio.save(new Usuario(datosRegistro));
        RespuestaUsuario datosRespuesta = new RespuestaUsuario(usuario);
        URI url = uri.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<ListaUsuario>> listar(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepositorio.findAll(paginacion).map(ListaUsuario::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaUsuarioOld> retornaDatos(@PathVariable Long id) {
        Usuario usuario = usuarioRepositorio.getReferenceById(id);
        return ResponseEntity.ok(new RespuestaUsuarioOld(usuario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaUsuario> actualizar(@RequestBody ActualizarUsuario datosActualizar) {
        Usuario usuario = usuarioRepositorio.getReferenceById(datosActualizar.id());
        usuario.actualizarDatos(datosActualizar);
        return ResponseEntity.ok( new RespuestaUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Usuario usuario = usuarioRepositorio.getReferenceById(id);
        usuarioRepositorio.delete(usuario);
        return ResponseEntity.noContent().build();
    }
}
