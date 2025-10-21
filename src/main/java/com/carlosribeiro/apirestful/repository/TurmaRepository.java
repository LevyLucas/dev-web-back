package com.carlosribeiro.apirestful.repository;

import com.carlosribeiro.apirestful.dto.TurmaListDTO;
import com.carlosribeiro.apirestful.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

    @Query("""
           select new com.carlosribeiro.apirestful.dto.TurmaListDTO(
             t.id, t.codigo, t.ano, t.periodo, d.nome, p.nome, count(i.id)
           )
           from Turma t
           join t.professor p
           join t.disciplina d
           left join t.inscricoes i
           group by t.id, t.codigo, t.ano, t.periodo, d.nome, p.nome
           order by t.codigo asc
           """)
    List<TurmaListDTO> listarResumo();

    @Query("""
           select new com.carlosribeiro.apirestful.dto.TurmaListDTO(
             t.id, t.codigo, t.ano, t.periodo, d.nome, p.nome, count(i.id)
           )
           from Turma t
           join t.professor p
           join t.disciplina d
           left join t.inscricoes i
           where lower(t.codigo) like lower(concat(:prefixo, '%'))
           group by t.id, t.codigo, t.ano, t.periodo, d.nome, p.nome
           order by t.codigo asc
           """)
    List<TurmaListDTO> buscarPorCodigoPrefixo(String prefixo);

    @Query("""
           select distinct t from Turma t
           join fetch t.professor
           join fetch t.disciplina
           left join fetch t.inscricoes i
           left join fetch i.aluno
           where t.id = :id
           """)
    Optional<Turma> buscarDetalhe(Long id);
}
