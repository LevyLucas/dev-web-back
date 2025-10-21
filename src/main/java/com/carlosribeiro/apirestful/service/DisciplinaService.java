package com.carlosribeiro.apirestful.service;

import com.carlosribeiro.apirestful.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.apirestful.model.Disciplina;
import com.carlosribeiro.apirestful.repository.DisciplinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DisciplinaService {

    private final DisciplinaRepository repo;

    public List<Disciplina> listar() { return repo.findAll(); }

    public Disciplina buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() ->
            new EntidadeNaoEncontradaException("Disciplina com id = " + id + " não encontrada."));
    }

    public Disciplina incluir(Disciplina d) { return repo.save(d); }

    public Disciplina alterar(Long id, Disciplina d) {
        buscarPorId(id);
        d.setId(id);
        return repo.save(d);
    }

    public void remover(Long id) {
        var d = buscarPorId(id);
        repo.delete(d);
    }
}
