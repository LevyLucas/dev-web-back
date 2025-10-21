package com.carlosribeiro.apirestful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "turma")
public class Turma {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer ano;

    @NotBlank @Size(max = 20)
    private String periodo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "disciplina_id", nullable = false)
    private Disciplina disciplina;

    @OneToMany(mappedBy = "turma")
    @JsonIgnore
    @Builder.Default
    private List<Inscricao> inscricoes = new ArrayList<>();
}
