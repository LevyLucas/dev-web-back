package com.carlosribeiro.apirestful.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "disciplina",
        uniqueConstraints = @UniqueConstraint(name="uk_disciplina_nome", columnNames = "nome"))
public class Disciplina {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String nome;

    @NotNull
    private Integer cargaHoraria; // horas
}
