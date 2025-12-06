package com.carlosribeiro.apirestful.service;

import com.carlosribeiro.apirestful.model.Role;
import com.carlosribeiro.apirestful.model.Usuario;
import com.carlosribeiro.apirestful.repository.UsuarioRepository;
import java.util.Set;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repo;
    private final PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository repo, @Lazy PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public Usuario cadastrarUsuarioPadrao(String nome, String username, String senha) {
        if (repo.existsByUsername(username)) {
            throw new IllegalArgumentException("Usuário já existe");
        }
        Usuario u = new Usuario();
        u.setNome(nome);
        u.setUsername(username);
        u.setPassword(encoder.encode(senha));
        u.getRoles().add(Role.ROLE_USER);
        return repo.save(u);
    }

    public Usuario cadastrarUsuario(String nome, String username, String senha, Set<Role> roles) {
        if (repo.existsByUsername(username)) {
            throw new IllegalArgumentException("Usuário já existe");
        }
        Usuario u = new Usuario();
        u.setNome(nome);
        u.setUsername(username);
        u.setPassword(encoder.encode(senha));
        u.setRoles(roles);
        return repo.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new User(
                u.getUsername(),
                u.getPassword(),
                u.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority(r.name()))
                        .toList()
        );
    }
}
