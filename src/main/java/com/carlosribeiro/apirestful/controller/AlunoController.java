package com.carlosribeiro.apirestful.controller;

import com.carlosribeiro.apirestful.model.Aluno;
import com.carlosribeiro.apirestful.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public List<Aluno> listar(@RequestParam(required = false) String nome) {
        if (nome != null && !nome.isBlank()) {
            return alunoService.buscarPorNome(nome);
        }
        return alunoService.listar();
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public Aluno buscarPorId(@PathVariable Long id) {
        return alunoService.buscarPorId(id);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/matricula/{matricula}")
    public Aluno buscarPorMatricula(@PathVariable String matricula) {
        return alunoService.buscarPorMatricula(matricula);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/dto")
    public List<com.carlosribeiro.apirestful.dto.AlunoDTO> listarDTO() {
        return alunoService.listarDTO();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno incluir(@Valid @RequestBody Aluno aluno) {
        return alunoService.incluir(aluno);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Aluno alterar(@PathVariable Long id, @Valid @RequestBody Aluno aluno) {
        aluno.setId(id);
        return alunoService.alterar(aluno);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        alunoService.remover(id);
    }
}
