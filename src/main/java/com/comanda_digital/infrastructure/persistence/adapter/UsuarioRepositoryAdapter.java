package com.comanda_digital.infrastructure.persistence.adapter;

import com.comanda_digital.application.port.UsuarioRepositoryPort;
import com.comanda_digital.domain.model.Usuario;
import com.comanda_digital.infrastructure.persistence.repository.UsuarioJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository usuarioJpaRepository;

    public UsuarioRepositoryAdapter(UsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioJpaRepository.findById(id);
    }
}