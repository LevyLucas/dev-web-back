package com.carlosribeiro.apirestful.dto;

public record TurmaListDTO(
        Long id,
        Integer ano,
        String periodo,
        String disciplinaNome,
        String professorNome,
        Long totalAlunos
) { }
