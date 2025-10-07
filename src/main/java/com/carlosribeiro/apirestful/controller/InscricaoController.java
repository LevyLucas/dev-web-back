package com.carlosribeiro.apirestful.controller;

import com.carlosribeiro.apirestful.controller.dto.InscricaoRequest;
import com.carlosribeiro.apirestful.model.Inscricao;
import com.carlosribeiro.apirestful.service.InscricaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {

    private final InscricaoService inscricaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Inscricao cadastrar(@Valid @RequestBody InscricaoRequest req) {
        return inscricaoService.cadastrar(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        inscricaoService.remover(id);
    }
}
