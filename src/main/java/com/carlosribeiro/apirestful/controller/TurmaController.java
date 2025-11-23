package com.carlosribeiro.apirestful.controller;

import com.carlosribeiro.apirestful.controller.dto.TurmaRequest;
import com.carlosribeiro.apirestful.dto.AlunoDTO;
import com.carlosribeiro.apirestful.dto.TurmaDetalheDTO;
import com.carlosribeiro.apirestful.dto.TurmaListDTO;
import com.carlosribeiro.apirestful.model.Turma;
import com.carlosribeiro.apirestful.service.TurmaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<TurmaListDTO> listar() {
        return turmaService.listarResumo();
    }

    @GetMapping("/search")
    public List<TurmaListDTO> search(@RequestParam String q) {
        return turmaService.buscarPorCodigoPrefixo(q);
    }

    @GetMapping("/{id}")
    public TurmaDetalheDTO detalhe(@PathVariable Long id) {
        return turmaService.buscarDetalhe(id);
    }

    @GetMapping("/{id}/alunos")
    public Page<AlunoDTO> alunos(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return turmaService.alunosPaginado(id, page, size);
    }

    @GetMapping("/disciplinas/{disciplinaId}")
    public List<Turma> turmasDaDisciplina(@PathVariable Long disciplinaId) {
        return turmaService.listarPorDisciplina(disciplinaId);
    }

    @GetMapping("/por-disciplina")
    public List<TurmaListDTO> turmasPorDisciplina(@RequestParam Long disciplinaId) {
        return turmaService.listarResumoPorDisciplina(disciplinaId);
    }

    @GetMapping("/{id}/alunos/disponiveis")
    public Page<AlunoDTO> alunosDisponiveis(
            @PathVariable Long id,
            @RequestParam(defaultValue = "") String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return turmaService.alunosDisponiveis(id, q, page, size);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        turmaService.remover(id);
    }
}
