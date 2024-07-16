package com.alura.foro.api.Dto.Topicos;

import com.alura.foro.api.Modelo.Topico;

public record RespuestaTopicos(String titulo, String mensaje, String autor, String curso) {

    public RespuestaTopicos(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getAutor().getNombre(), topico.getCurso().getNombre());
    }
}
