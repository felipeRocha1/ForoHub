package com.alura.foro.api.Dto.Topicos;

import com.alura.foro.api.Dto.Curso.DatosRespuesta;
import com.alura.foro.api.Dto.usuario.RespuestaUsuario;
import com.alura.foro.api.Modelo.Topico;

public record RespuestaTopicosOld(Long id, String titulo, String mensaje, String fechaCreacion, String estado, RespuestaUsuario autor,
                                  DatosRespuesta curso) {

    public RespuestaTopicosOld(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion().toString(),
                topico.getEstado().toString(), new RespuestaUsuario(topico.getAutor()),
                new DatosRespuesta(topico.getCurso()));
    }
}
