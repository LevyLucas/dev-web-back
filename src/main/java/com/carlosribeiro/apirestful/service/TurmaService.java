package com.carlosribeiro.apirestful.service;

import com.carlosribeiro.apirestful.controller.dto.TurmaRequest;
import com.carlosribeiro.apirestful.dto.*;
import com.carlosribeiro.apirestful.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.apirestful.model.Disciplina;
import com.carlosribeiro.apirestful.model.Professor;
import com.carlosribeiro.apirestful.model.Turma;
import com.carlosribeiro.apirestful.repository.DisciplinaRepository;
import com.carlosribeiro.apirestful.repository.ProfessorRepository;
import com.carlosribeiro.apirestful.repository.TurmaRepository;
import com.carlosribeiro.apirestful.repository.InscricaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final ProfessorRepository professorRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final InscricaoRepository inscricaoRepository;

    public List<TurmaListDTO> listarResumo() {
        return turmaRepository.listarResumo();
    }

    public List<TurmaListDTO> buscarPorCodigoPrefixo(String q) {
        if (q == null || q.isBlank()) return List.of();
        return turmaRepository.buscarPorCodigoPrefixo(q.trim());
    }

    public TurmaDetalheDTO buscarDetalhe(Long id) {
        Turma t = turmaRepository.buscarDetalhe(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Turma com id = " + id + " não encontrada."));

        var professorDTO = new ProfessorDTO(t.getProfessor().getId(), t.getProfessor().getNome(), t.getProfessor().getEmail());
        var disciplinaDTO = new DisciplinaDTO(t.getDisciplina().getId(), t.getDisciplina().getNome(), t.getDisciplina().getCargaHoraria());
        var alunos = t.getInscricoes().stream()
            .map(i -> new AlunoDTO(i.getAluno().getId(), i.getAluno().getNome(), i.getAluno().getEmail()))
            .toList();

        return new TurmaDetalheDTO(t.getId(), t.getAno(), t.getPeriodo(), disciplinaDTO, professorDTO, alunos);
    }

    public org.springframework.data.domain.Page<AlunoDTO> alunosPaginado(Long turmaId, int page, int size) { // << NOVO
        var pageable = org.springframework.data.domain.PageRequest.of(page, size);
        return inscricaoRepository.listarAlunosPorTurma(turmaId, pageable);
    }

    @Transactional
    public Turma cadastrar(TurmaRequest req) {
        Professor prof = professorRepository.findById(req.professorId())
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Professor com id = " + req.professorId() + " não encontrado."));
        Disciplina disc = disciplinaRepository.findById(req.disciplinaId())
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Disciplina com id = " + req.disciplinaId() + " não encontrada."));

        Turma t = Turma.builder()
                .codigo(req.codigo())
                .ano(req.ano())
                .periodo(req.periodo())
                .professor(prof)
                .disciplina(disc)
                .build();

        return turmaRepository.save(t);
    }

    public void remover(Long id) {
        Turma t = turmaRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Turma com id = " + id + " não encontrada."));
        turmaRepository.delete(t);
    }
}
