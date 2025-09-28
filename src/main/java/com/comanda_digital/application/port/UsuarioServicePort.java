package com.comanda_digital.application.port;

import com.comanda_digital.application.dto.UsuarioRequestDTO;
import com.comanda_digital.application.dto.UsuarioResponseDTO;

public interface UsuarioServicePort {
    UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO);
}