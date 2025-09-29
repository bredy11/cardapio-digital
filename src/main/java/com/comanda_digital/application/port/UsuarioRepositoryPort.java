package com.comanda_digital.application.port;

import com.comanda_digital.domain.model.Usuario;
import java.util.Optional;

public interface UsuarioRepositoryPort {
    Optional<Usuario> findById(Long id);
}