package com.carlosribeiro.apirestful.controller.dto;

public record LoginRequest(
        String username,
        String password
) {}
