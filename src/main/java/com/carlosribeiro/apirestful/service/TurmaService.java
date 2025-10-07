package com.carlosribeiro.apirestful.service;

import com.carlosribeiro.apirestful.controller.dto.TurmaRequest;
import com.carlosribeiro.apirestful.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.apirestful.model.Professor;
import com.carlosribeiro.apirestful.model.Turma;
import com.carlosribeiro.apirestful.repository.ProfessorRepository;
import com.carlosribeiro.apirestful.repository.TurmaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final ProfessorRepository professorRepository;

    @Transactional
    public Turma cadastrar(TurmaRequest req) {
        Professor prof = professorRepository.findById(req.professorId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Professor com id = " + req.professorId() + " não encontrado."));

        Turma t = Turma.builder()
                .ano(req.ano())
                .periodo(req.periodo())
                .professor(prof)
                .build();

        return turmaRepository.save(t);
    }

    public void remover(Long id) {
        Turma t = turmaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Turma com id = " + id + " não encontrada."));
        turmaRepository.delete(t);
    }
}
