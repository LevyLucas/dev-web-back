package com.carlosribeiro.apirestful.service;

import com.carlosribeiro.apirestful.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.apirestful.model.Professor;
import com.carlosribeiro.apirestful.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public List<Professor> listar() {
        return professorRepository.findAll();
    }

    public Professor buscarPorId(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Professor com id = " + id + " não encontrado."));
    }

    public Professor incluir(Professor professor) {
        return professorRepository.save(professor);
    }

    @Transactional
    public Professor alterar(Long id, Professor professor) {
        buscarPorId(id);
        professor.setId(id);
        return professorRepository.save(professor);
    }

    public void remover(Long id) {
        professorRepository.delete(buscarPorId(id));
    }
}
