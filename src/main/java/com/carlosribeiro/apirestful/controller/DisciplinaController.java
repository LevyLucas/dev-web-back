package com.carlosribeiro.apirestful.controller;

import com.carlosribeiro.apirestful.model.Disciplina;
import com.carlosribeiro.apirestful.service.DisciplinaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaService service;

    @GetMapping
    public List<Disciplina> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Disciplina buscarPorId(@PathVariable Long id) { return service.buscarPorId(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Disciplina incluir(@Valid @RequestBody Disciplina d) { return service.incluir(d); }

    @PutMapping("/{id}")
    public Disciplina alterar(@PathVariable Long id, @Valid @RequestBody Disciplina d) {
        return service.alterar(id, d);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) { service.remover(id); }
}
