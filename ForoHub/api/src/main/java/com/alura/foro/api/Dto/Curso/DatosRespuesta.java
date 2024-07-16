package com.alura.foro.api.Dto.Curso;

import com.alura.foro.api.Modelo.Curso;

public record DatosRespuesta(String nombre, String categoria) {

    public DatosRespuesta(Curso curso) {
        this(curso.getNombre(), curso.getCategoria());
    }
}
