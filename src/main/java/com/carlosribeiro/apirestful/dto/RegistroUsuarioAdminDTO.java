package com.carlosribeiro.apirestful.dto;

import com.carlosribeiro.apirestful.model.Role;

public record RegistroUsuarioAdminDTO(
        String nome,
        String username,
        String password,
        Role role
) {}
