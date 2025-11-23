package com.carlosribeiro.apirestful.controller;

import com.carlosribeiro.apirestful.controller.dto.InscricaoRequest;
import com.carlosribeiro.apirestful.dto.AlunoDTO;
import com.carlosribeiro.apirestful.dto.InscricaoDTO;
import com.carlosribeiro.apirestful.repository.AlunoRepository;
import com.carlosribeiro.apirestful.service.InscricaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {

    private final InscricaoService inscricaoService;
    private final AlunoRepository alunoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InscricaoDTO cadastrar(@Valid @RequestBody InscricaoRequest req) {
        return inscricaoService.cadastrar(req);
    }

    @GetMapping("/disponiveis")
    public List<AlunoDTO> alunosDisponiveis(@RequestParam Long turmaId) {
        return alunoRepository.listarDisponiveisPorTurma(turmaId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        inscricaoService.remover(id);
    }
}
