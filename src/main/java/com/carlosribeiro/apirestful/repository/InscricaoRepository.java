package com.carlosribeiro.apirestful.repository;

import com.carlosribeiro.apirestful.dto.AlunoDTO;
import com.carlosribeiro.apirestful.model.Inscricao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    @Query("""
           select new com.carlosribeiro.apirestful.dto.AlunoDTO(a.id, a.nome, a.email)
           from Inscricao i
           join i.aluno a
           where i.turma.id = :turmaId
           order by i.id desc
           """)
    Page<AlunoDTO> listarAlunosPorTurma(@Param("turmaId") Long turmaId, Pageable pageable);

    @Query("select i.aluno.id from Inscricao i where i.turma.id = :turmaId")
    List<Long> findAlunoIdsByTurma(@Param("turmaId") Long turmaId);
}
