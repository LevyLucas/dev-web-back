package com.carlosribeiro.apirestful.repository;

import com.carlosribeiro.apirestful.dto.AlunoDTO;
import com.carlosribeiro.apirestful.model.Inscricao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    @Query("""
           select new com.carlosribeiro.apirestful.dto.AlunoDTO(a.id, a.nome, a.email)
           from Inscricao i
           join i.aluno a
           where i.turma.id = :turmaId
           order by a.nome asc
           """)
    Page<AlunoDTO> listarAlunosPorTurma(@Param("turmaId") Long turmaId, Pageable pageable);
}
