package com.alura.foro.api.Dto.Topicos;

import com.alura.foro.api.Modelo.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActualizarTopicos(
        @NotNull
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Estado estado,
        @NotNull
        Long autorId,
        @NotNull
        Long cursoId) {
}
