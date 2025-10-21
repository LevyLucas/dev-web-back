package com.carlosribeiro.apirestful.dto;

public record TurmaListDTO(
        Long id,
        String codigo,
        Integer ano,
        String periodo,
        String disciplinaNome,
        String professorNome,
        Long totalAlunos
) { }
