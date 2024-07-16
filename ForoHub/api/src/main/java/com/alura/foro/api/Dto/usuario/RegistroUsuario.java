package com.alura.foro.api.Dto.usuario;

import com.alura.foro.api.Modelo.Tipo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String contrasena,
        @NotNull
        Tipo tipo) {
}
