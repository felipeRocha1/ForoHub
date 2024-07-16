package com.alura.foro.api.Dto.Topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroTopicos(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje ,
        @NotNull
        Long autorId,
        @NotNull
        Long cursoId) {
}
