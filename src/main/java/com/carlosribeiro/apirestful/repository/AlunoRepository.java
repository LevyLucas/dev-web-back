package com.carlosribeiro.apirestful.repository;

import com.carlosribeiro.apirestful.model.Aluno;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByNome(String nome);

    Optional<Aluno> findByMatricula(String matricula);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Aluno> findWithLockById(Long id);

    Page<Aluno> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    Page<Aluno> findByNomeContainingIgnoreCaseAndIdNotIn(String nome, Collection<Long> ids, Pageable pageable);

    @Query("""
        select new com.carlosribeiro.apirestful.dto.AlunoDTO(a.id, a.nome, a.email)
        from Aluno a
        where a.id not in (select i.aluno.id from Inscricao i where i.turma.id = :turmaId)
        order by a.nome asc
    """)
    List<com.carlosribeiro.apirestful.dto.AlunoDTO> listarDisponiveisPorTurma(Long turmaId);
}
