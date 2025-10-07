package com.carlosribeiro.apirestful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "professor",
        uniqueConstraints = @UniqueConstraint(name="uk_prof_email", columnNames = "email"))
public class Professor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 120)
    private String nome;

    @NotBlank @Email @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String email;

    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    @Builder.Default
    private List<Turma> turmas = new ArrayList<>();
}
