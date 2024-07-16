package com.alura.foro.api.Control;

import com.alura.foro.api.Dto.Topicos.*;
import com.alura.foro.api.Modelo.Curso;
import com.alura.foro.api.Modelo.Topico;
import com.alura.foro.api.Modelo.Usuario;
import com.alura.foro.api.Repositorio.CursoRepositorio;
import com.alura.foro.api.Repositorio.TopicoRepositorio;
import com.alura.foro.api.Repositorio.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicosControl {

    private final TopicoRepositorio topicoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final CursoRepositorio cursoRepositorio;

    public TopicosControl(TopicoRepositorio topicoRepositorio, UsuarioRepositorio usuarioRepositorio, CursoRepositorio cursoRepositorio) {
        this.topicoRepositorio = topicoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.cursoRepositorio = cursoRepositorio;
    }

    @PostMapping
    public ResponseEntity<RespuestaTopicos> registrar(@RequestBody @Valid RegistroTopicos datosRegistro, UriComponentsBuilder uri) {
        Usuario autor = usuarioRepositorio.getReferenceById(datosRegistro.autorId());
        Curso curso = cursoRepositorio.getReferenceById(datosRegistro.cursoId());
        Topico topico = topicoRepositorio.save(new Topico(datosRegistro, autor, curso));
        RespuestaTopicos datosRespuesta = new RespuestaTopicos(topico);
        URI url = uri.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<ListadoTopicos>> listar(@PageableDefault(size = 10)Pageable paginacion) {
        return ResponseEntity.ok(topicoRepositorio.findAll(paginacion).map(ListadoTopicos::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaTopicosOld> retornaDatos(@PathVariable Long id) {
        Topico topico = topicoRepositorio.getReferenceById(id);
        return ResponseEntity.ok(new RespuestaTopicosOld(topico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaTopicos> actualizar(@RequestBody @Valid ActualizarTopicos datosActualizar) {
        Usuario autor = usuarioRepositorio.getReferenceById(datosActualizar.autorId());
        Curso curso = cursoRepositorio.getReferenceById(datosActualizar.cursoId());
        Topico topico = topicoRepositorio.getReferenceById(datosActualizar.id());
        topico.actualizarDatos(datosActualizar, autor, curso);
        return ResponseEntity.ok( new RespuestaTopicos(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Topico topico = topicoRepositorio.getReferenceById(id);
        //topicoRepository.delete(topico);
        topico.cerrarTopico();
        return ResponseEntity.noContent().build();
    }
}
