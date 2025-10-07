package com.carlosribeiro.apirestful.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "aluno",
       uniqueConstraints = {
           @UniqueConstraint(name = "uk_aluno_matricula", columnNames = "matricula"),
           @UniqueConstraint(name = "uk_aluno_email", columnNames = "email")
       })
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 120)
    private String nome;

    @NotBlank(message = "Matrícula é obrigatória")
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String matricula;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String email;

    @Size(max = 80)
    private String curso;

    @Past(message = "Data de nascimento deve estar no passado")
    private LocalDate dataNascimento;

    @Builder.Default
    private Boolean ativo = true;
}
