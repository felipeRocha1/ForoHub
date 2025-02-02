package com.alura.foro.api.Dto.Respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActualizarRespuesta(
        @NotNull
        Long id,
        @NotBlank
        String mensaje,
        @NotNull
        Long topicoId,
        @NotBlank
        Long autorId,
        @NotNull
        Boolean solucion) {
}
