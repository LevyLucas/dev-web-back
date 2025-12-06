package com.carlosribeiro.apirestful.controller;

import com.carlosribeiro.apirestful.config.JwtUtil;
import com.carlosribeiro.apirestful.controller.dto.LoginRequest;
import com.carlosribeiro.apirestful.controller.dto.LoginResponse;
import com.carlosribeiro.apirestful.dto.RegistroUsuarioAdminDTO;
import com.carlosribeiro.apirestful.dto.RegistroUsuarioDTO;
import com.carlosribeiro.apirestful.dto.UsuarioDTO;
import com.carlosribeiro.apirestful.model.Role;
import com.carlosribeiro.apirestful.model.Usuario;
import com.carlosribeiro.apirestful.service.UsuarioService;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UsuarioService usuarioService;

    public AuthController(
            AuthenticationManager authManager,
            JwtUtil jwtUtil,
            UsuarioService usuarioService
    ) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password())
        );

        UserDetails user = (UserDetails) auth.getPrincipal();
        String token = jwtUtil.gerarToken(user);

        Set<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        return new LoginResponse(user.getUsername(), user.getUsername(), roles, token);
    }

    @PostMapping("/register")
    public UsuarioDTO registrar(@RequestBody RegistroUsuarioDTO dto) {
        Usuario u = usuarioService.cadastrarUsuarioPadrao(dto.nome(), dto.username(), dto.password());
        return UsuarioDTO.fromEntity(u);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/users")
    public UsuarioDTO criarUsuario(@RequestBody RegistroUsuarioAdminDTO dto) {
        Set<Role> roles = Set.of(dto.role());
        Usuario u = usuarioService.cadastrarUsuario(dto.nome(), dto.username(), dto.password(), roles);
        return UsuarioDTO.fromEntity(u);
    }
}
