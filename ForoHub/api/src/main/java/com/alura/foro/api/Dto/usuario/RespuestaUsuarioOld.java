package com.alura.foro.api.Dto.usuario;

import com.alura.foro.api.Modelo.Usuario;

public record RespuestaUsuarioOld(String nombre, String email, String contrasena, String tipo) {

    public RespuestaUsuarioOld(Usuario usuario) {
        this(usuario.getNombre(), usuario.getEmail(), usuario.getContrasena(), usuario.getTipo().toString());
    }
}
