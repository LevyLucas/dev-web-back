package com.carlosribeiro.apirestful.service;

import com.carlosribeiro.apirestful.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.apirestful.model.Aluno;
import com.carlosribeiro.apirestful.repository.AlunoRepository;
import com.carlosribeiro.apirestful.repository.InscricaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final InscricaoRepository inscricaoRepository;

    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    public List<Aluno> buscarPorNome(String nome) {
        return alunoRepository.findByNome(nome);
    }

    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Aluno com id = " + id + " não encontrado."));
    }

    public Aluno buscarPorMatricula(String matricula) {
        return alunoRepository.findByMatricula(matricula)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Aluno com matrícula = " + matricula + " não encontrado."));
    }

    public Aluno incluir(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public List<com.carlosribeiro.apirestful.dto.AlunoDTO> listarDTO() {
        return alunoRepository.findAll().stream()
                .map(a -> new com.carlosribeiro.apirestful.dto.AlunoDTO(a.getId(), a.getNome(), a.getEmail()))
                .toList();
    }

    @Transactional
    public Aluno alterar(Aluno aluno) {
        alunoRepository.findWithLockById(aluno.getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Aluno com id = " + aluno.getId() + " não encontrado."));
        return alunoRepository.save(aluno);
    }

    public void remover(Long id) {
        buscarPorId(id);

        if (inscricaoRepository.existsByAlunoId(id)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não é possível remover aluno inscrito em turma."
            );
        }

        alunoRepository.deleteById(id);
    }
}
