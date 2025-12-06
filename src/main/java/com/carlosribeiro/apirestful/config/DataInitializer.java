package com.carlosribeiro.apirestful.config;

import com.carlosribeiro.apirestful.model.*;
import com.carlosribeiro.apirestful.repository.*;
import com.carlosribeiro.apirestful.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioService usuarioService;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final TurmaRepository turmaRepository;
    private final InscricaoRepository inscricaoRepository;

    public DataInitializer(
            UsuarioService usuarioService,
            AlunoRepository alunoRepository,
            ProfessorRepository professorRepository,
            DisciplinaRepository disciplinaRepository,
            TurmaRepository turmaRepository,
            InscricaoRepository inscricaoRepository
    ) {
        this.usuarioService = usuarioService;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.turmaRepository = turmaRepository;
        this.inscricaoRepository = inscricaoRepository;
    }

    @Override
    public void run(String... args) {

        usuarioService.cadastrarUsuario(
                "Administrador",
                "admin@mail.com",
                "password",
                Set.of(Role.ROLE_ADMIN)
        );

        usuarioService.cadastrarUsuario(
                "Usuário Padrão",
                "user@mail.com",
                "password",
                Set.of(Role.ROLE_USER)
        );

        if (professorRepository.count() == 0) {

            Professor p1 = new Professor();
            p1.setNome("Carlos Ribeiro");
            p1.setEmail("carlos.ribeiro@uff.br");

            Professor p2 = new Professor();
            p2.setNome("Ana Paula Fernandes");
            p2.setEmail("ana.fernandes@uff.br");

            Professor p3 = new Professor();
            p3.setNome("Ricardo Gomes");
            p3.setEmail("ricardo.gomes@uff.br");

            professorRepository.save(p1);
            professorRepository.save(p2);
            professorRepository.save(p3);
        }

        var professores = professorRepository.findAll();

        if (disciplinaRepository.count() == 0) {

            Disciplina d1 = new Disciplina();
            d1.setNome("Algoritmos e Estruturas de Dados");
            d1.setCargaHoraria(60);

            Disciplina d2 = new Disciplina();
            d2.setNome("Banco de Dados");
            d2.setCargaHoraria(60);

            Disciplina d3 = new Disciplina();
            d3.setNome("Desenvolvimento Web");
            d3.setCargaHoraria(60);

            disciplinaRepository.save(d1);
            disciplinaRepository.save(d2);
            disciplinaRepository.save(d3);
        }

        var disciplinas = disciplinaRepository.findAll();

        if (turmaRepository.count() == 0) {

            Turma t1 = new Turma();
            t1.setAno(2024);
            t1.setPeriodo("1");
            t1.setCodigo("ALG-2024-1");
            t1.setProfessor(professores.get(0));
            t1.setDisciplina(disciplinas.get(0));

            Turma t2 = new Turma();
            t2.setAno(2024);
            t2.setPeriodo("2");
            t2.setCodigo("BD-2024-2");
            t2.setProfessor(professores.get(1));
            t2.setDisciplina(disciplinas.get(1));

            Turma t3 = new Turma();
            t3.setAno(2025);
            t3.setPeriodo("1");
            t3.setCodigo("WEB-2025-1");
            t3.setProfessor(professores.get(2));
            t3.setDisciplina(disciplinas.get(2));

            turmaRepository.save(t1);
            turmaRepository.save(t2);
            turmaRepository.save(t3);
        }

        var turmas = turmaRepository.findAll();

        if (alunoRepository.count() == 0) {

            Aluno a1 = new Aluno();
            a1.setNome("João da Silva");
            a1.setEmail("joao.silva@mail.com");
            a1.setMatricula("20250001");

            Aluno a2 = new Aluno();
            a2.setNome("Maria Oliveira");
            a2.setEmail("maria.oliveira@mail.com");
            a2.setMatricula("20250002");

            Aluno a3 = new Aluno();
            a3.setNome("Pedro Souza");
            a3.setEmail("pedro.souza@mail.com");
            a3.setMatricula("20250003");

            Aluno a4 = new Aluno();
            a4.setNome("Carla Nunes");
            a4.setEmail("carla.nunes@mail.com");
            a4.setMatricula("20250004");

            Aluno a5 = new Aluno();
            a5.setNome("Lucas Mendes");
            a5.setEmail("lucas.mendes@mail.com");
            a5.setMatricula("20250005");

            alunoRepository.save(a1);
            alunoRepository.save(a2);
            alunoRepository.save(a3);
            alunoRepository.save(a4);
            alunoRepository.save(a5);
        }

        var alunos = alunoRepository.findAll();

        if (inscricaoRepository.count() == 0) {

            Inscricao i1 = new Inscricao();
            i1.setAluno(alunos.get(0));
            i1.setTurma(turmas.get(0));
            i1.setDataHora(LocalDateTime.now().minusDays(10));
            inscricaoRepository.save(i1);

            Inscricao i2 = new Inscricao();
            i2.setAluno(alunos.get(1));
            i2.setTurma(turmas.get(0));
            i2.setDataHora(LocalDateTime.now().minusDays(5));
            inscricaoRepository.save(i2);

            Inscricao i3 = new Inscricao();
            i3.setAluno(alunos.get(2));
            i3.setTurma(turmas.get(1));
            i3.setDataHora(LocalDateTime.now().minusDays(2));
            inscricaoRepository.save(i3);
        }
    }
}
