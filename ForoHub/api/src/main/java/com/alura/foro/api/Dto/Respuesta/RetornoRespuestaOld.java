package com.alura.foro.api.Dto.Respuesta;

import com.alura.foro.api.Dto.Topicos.RespuestaTopicos;
import com.alura.foro.api.Dto.usuario.RespuestaUsuario;
import com.alura.foro.api.Modelo.Respuesta;

public record RetornoRespuestaOld(String mensaje, RespuestaTopicos topico, String fechaCreacion, RespuestaUsuario autor, String solucion) {

    public RetornoRespuestaOld(Respuesta respuesta) {
        this(respuesta.getMensaje(), new RespuestaTopicos(respuesta.getTopico()), respuesta.getFechaCreacion().toString(),
                new RespuestaUsuario(respuesta.getAutor()), respuesta.getSolucion().toString());
    }
}
