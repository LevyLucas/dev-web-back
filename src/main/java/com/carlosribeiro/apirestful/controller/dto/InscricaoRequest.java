package com.carlosribeiro.apirestful.controller.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record InscricaoRequest(
        @NotNull Long alunoId,
        @NotNull Long turmaId,
        LocalDateTime dataHora // opcional, se vier null usa data/hora atual
) {}
