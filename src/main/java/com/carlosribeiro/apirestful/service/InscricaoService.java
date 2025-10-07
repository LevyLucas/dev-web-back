package com.carlosribeiro.apirestful.service;

import com.carlosribeiro.apirestful.controller.dto.InscricaoRequest;
import com.carlosribeiro.apirestful.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.apirestful.model.Aluno;
import com.carlosribeiro.apirestful.model.Inscricao;
import com.carlosribeiro.apirestful.model.Turma;
import com.carlosribeiro.apirestful.repository.AlunoRepository;
import com.carlosribeiro.apirestful.repository.InscricaoRepository;
import com.carlosribeiro.apirestful.repository.TurmaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class InscricaoService {

    private final InscricaoRepository inscricaoRepository;
    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;

    @Transactional
    public Inscricao cadastrar(InscricaoRequest req) {
        Aluno aluno = alunoRepository.findById(req.alunoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Aluno com id = " + req.alunoId() + " não encontrado."));
        Turma turma = turmaRepository.findById(req.turmaId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Turma com id = " + req.turmaId() + " não encontrada."));

        Inscricao i = Inscricao.builder()
                .aluno(aluno)
                .turma(turma)
                .dataHora(req.dataHora() != null ? req.dataHora() : LocalDateTime.now())
                .build();

        return inscricaoRepository.save(i);
    }

    public void remover(Long id) {
        Inscricao i = inscricaoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Inscrição com id = " + id + " não encontrada."));
        inscricaoRepository.delete(i);
    }
}
