package com.comanda_digital.application.port;

import com.comanda_digital.application.dto.usuario.UsuarioRequestDTO;
import com.comanda_digital.application.dto.usuario.UsuarioResponseDTO;

public interface UsuarioServicePort {
    UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO);
}