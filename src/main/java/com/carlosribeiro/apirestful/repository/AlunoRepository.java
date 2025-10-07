package com.carlosribeiro.apirestful.repository;

import com.carlosribeiro.apirestful.model.Aluno;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByNome(String nome);

    Optional<Aluno> findByMatricula(String matricula);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Aluno> findWithLockById(Long id);
}
