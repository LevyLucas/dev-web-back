package com.carlosribeiro.apirestful.service;

import com.carlosribeiro.apirestful.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.apirestful.model.Aluno;
import com.carlosribeiro.apirestful.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

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

    public java.util.List<com.carlosribeiro.apirestful.dto.AlunoDTO> listarDTO() {
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
        alunoRepository.deleteById(id);
    }
}
