package com.alura.foro.api.Dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AutentificarUsuario(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String contrasena) {
}
