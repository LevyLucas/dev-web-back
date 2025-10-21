package com.carlosribeiro.apirestful.controller;

import com.carlosribeiro.apirestful.controller.dto.TurmaRequest;
import com.carlosribeiro.apirestful.model.Turma;
import com.carlosribeiro.apirestful.service.TurmaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Turma cadastrar(@Valid @RequestBody TurmaRequest req) {
        return turmaService.cadastrar(req);
    }

    @GetMapping
    public java.util.List<com.carlosribeiro.apirestful.dto.TurmaListDTO> listar() {
        return turmaService.listarResumo();
    }

    @GetMapping("/{id}")
    public com.carlosribeiro.apirestful.dto.TurmaDetalheDTO detalhe(@PathVariable Long id) {
        return turmaService.buscarDetalhe(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        turmaService.remover(id);
    }
}
