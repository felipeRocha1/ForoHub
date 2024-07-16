package com.alura.foro.api.Dto.Respuesta;

import com.alura.foro.api.Modelo.Respuesta;

public record RetornoRespuesta(String mensaje, String topico, String autor) {

    public RetornoRespuesta(Respuesta respuesta) {
        this(respuesta.getMensaje(), respuesta.getTopico().getTitulo(), respuesta.getAutor().getNombre());
    }
}
