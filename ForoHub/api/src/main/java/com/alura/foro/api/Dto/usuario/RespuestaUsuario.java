package com.alura.foro.api.Dto.usuario;

import com.alura.foro.api.Modelo.Usuario;

public record RespuestaUsuario(String nombre, String email, String tipo) {

    public RespuestaUsuario(Usuario usuario) {
        this(usuario.getNombre(), usuario.getEmail(), usuario.getTipo().toString());
    }
}
