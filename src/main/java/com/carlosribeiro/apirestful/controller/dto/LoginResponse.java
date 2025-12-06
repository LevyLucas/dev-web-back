package com.carlosribeiro.apirestful.controller.dto;

import java.util.Set;

public record LoginResponse(
        String username,
        String nome,
        Set<String> roles,
        String token
) {}
