package com.carlosribeiro.apirestful.repository;

import com.carlosribeiro.apirestful.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> { }
