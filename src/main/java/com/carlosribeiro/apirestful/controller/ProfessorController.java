package com.carlosribeiro.apirestful.controller;

import com.carlosribeiro.apirestful.model.Professor;
import com.carlosribeiro.apirestful.service.ProfessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping
    public List<Professor> listar() { return professorService.listar(); }

    @GetMapping("/{id}")
    public Professor buscarPorId(@PathVariable Long id) { return professorService.buscarPorId(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Professor incluir(@Valid @RequestBody Professor professor) {
        return professorService.incluir(professor);
    }

    @PutMapping("/{id}")
    public Professor alterar(@PathVariable Long id, @Valid @RequestBody Professor professor) {
        return professorService.alterar(id, professor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) { professorService.remover(id); }
}
