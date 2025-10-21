package com.carlosribeiro.apirestful.controller.dto;

import jakarta.validation.constraints.*;

public record TurmaRequest(
        @NotNull Integer ano,
        @NotBlank String periodo,
        @NotNull Long professorId,
        @NotNull Long disciplinaId
) {}
