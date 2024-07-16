package com.alura.foro.api.Dto.Topicos;

import com.alura.foro.api.Modelo.Topico;

public record ListadoTopicos(Long id, String titulo, String mensaje, String autor, String curso) {

    public ListadoTopicos(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor().getNombre(),
                topico.getCurso().getNombre());
    }
}
