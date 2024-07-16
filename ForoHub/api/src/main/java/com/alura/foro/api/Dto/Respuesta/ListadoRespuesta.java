package com.alura.foro.api.Dto.Respuesta;

import com.alura.foro.api.Modelo.Respuesta;

public record ListadoRespuesta(Long id, String mensaje, String topico, String autor) {

    public ListadoRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico().getTitulo(), respuesta.getAutor().getNombre());
    }
}
