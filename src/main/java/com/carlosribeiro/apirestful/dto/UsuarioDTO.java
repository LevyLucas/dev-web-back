package com.carlosribeiro.apirestful.dto;

import com.carlosribeiro.apirestful.model.Usuario;
import java.util.Set;
import java.util.stream.Collectors;

public record UsuarioDTO(
        Long id,
        String nome,
        String username,
        Set<String> roles
) {
    public static UsuarioDTO fromEntity(Usuario u) {
        Set<String> roles = u.getRoles()
                .stream()
                .map(Enum::name)
                .collect(Collectors.toSet());
        return new UsuarioDTO(u.getId(), u.getNome(), u.getUsername(), roles);
    }
}
