package com.comanda_digital.application.service;

import com.comanda_digital.application.dto.UsuarioRequestDTO;
import com.comanda_digital.application.dto.UsuarioResponseDTO;
import com.comanda_digital.application.port.UsuarioServicePort;
import com.comanda_digital.domain.model.Usuario;
import com.comanda_digital.infrastructure.persistence.UsuarioRepository;
import com.comanda_digital.shared.exception.EmailAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioServicePort {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        Optional<Usuario> existingUser = usuarioRepository.findByEmail(usuarioRequestDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("O email '" + usuarioRequestDTO.getEmail() + "' já está cadastrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setSenha(hashPassword(usuarioRequestDTO.getSenha()));
        usuario.setTelefone(usuarioRequestDTO.getTelefone());
        usuario.setStatus("ATIVO");
        usuario.setDataCriacao(LocalDateTime.now());

        Usuario savedUsuario = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(
                savedUsuario.getId(),
                savedUsuario.getNome(),
                savedUsuario.getEmail(),
                savedUsuario.getTelefone(),
                savedUsuario.getStatus(),
                savedUsuario.getDataCriacao()
        );
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao fazer hash da senha", e);
        }
    }
}