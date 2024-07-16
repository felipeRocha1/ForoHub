package com.alura.foro.api.Dto.usuario;

import com.alura.foro.api.Modelo.Usuario;

public record ListaUsuario(Long id, String nombre, String email, String tipo) {

    public ListaUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getTipo().toString());
    }
}
