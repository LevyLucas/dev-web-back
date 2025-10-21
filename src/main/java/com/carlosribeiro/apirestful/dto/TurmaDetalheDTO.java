package com.carlosribeiro.apirestful.dto;

import java.util.List;

public record TurmaDetalheDTO(
        Long id,
        Integer ano,
        String periodo,
        DisciplinaDTO disciplina,
        ProfessorDTO professor,
        List<AlunoDTO> alunos
) { }
