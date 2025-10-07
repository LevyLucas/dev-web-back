package com.carlosribeiro.apirestful.repository;

import com.carlosribeiro.apirestful.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> { }
