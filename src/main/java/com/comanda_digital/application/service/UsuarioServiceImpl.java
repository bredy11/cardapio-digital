package com.comanda_digital.application.service;

import com.comanda_digital.application.dto.usuario.UsuarioRequestDTO;
import com.comanda_digital.application.dto.usuario.UsuarioResponseDTO;
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

    public static final String STATUS = "ATIVO";
    public static final String ERRO_AO_FAZER_HASH_DA_SENHA = "Erro ao fazer hash da senha";
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
        usuario.setStatus(STATUS);
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
            throw new RuntimeException(ERRO_AO_FAZER_HASH_DA_SENHA, e);
        }
    }
}