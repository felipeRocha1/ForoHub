package com.alura.foro.api.Dto.usuario;

import com.alura.foro.api.Modelo.Tipo;

public record ActualizarUsuario(Long id, String nombre, String email, String contrasena, Tipo tipo) {
}
