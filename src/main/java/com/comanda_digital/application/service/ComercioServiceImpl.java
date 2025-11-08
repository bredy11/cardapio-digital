package com.comanda_digital.application.service;

import com.comanda_digital.application.dto.restaurante.ComercioRequestDTO;
import com.comanda_digital.application.dto.restaurante.ComercioResponseDTO;
import com.comanda_digital.application.port.ComercioServicePort;
import com.comanda_digital.domain.model.Comercio;
import com.comanda_digital.domain.model.Usuario;
import com.comanda_digital.infrastructure.cloud.ImagemClient;
import com.comanda_digital.infrastructure.cloud.ImagemUploadResponse;
import com.comanda_digital.infrastructure.persistence.ComercioRepository;
import com.comanda_digital.infrastructure.persistence.TipoComercioRepository;
import com.comanda_digital.infrastructure.persistence.UsuarioRepository;
import com.comanda_digital.shared.exception.EmailAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ComercioServiceImpl implements ComercioServicePort {

    private ComercioRepository comercioRepository;
    private UsuarioRepository usuarioRepository;
    private TipoComercioRepository tipoComercioRepository;
    private ImagemClient imagemClient;


    @Override
    public ComercioResponseDTO cadastrarComercio(ComercioRequestDTO comercioRequestDTO) {
        Optional<Usuario> existingUser = usuarioRepository.findById(comercioRequestDTO.getUsuarioId());

        if (!existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Usuario não localizado");
        }

        ImagemUploadResponse imagemUploadResponse = imagemClient.uploadImagem(comercioRequestDTO.getImagem(), comercioRequestDTO.getUsuarioId());

        Comercio comercio = new Comercio();
        comercio.setNome(comercioRequestDTO.getNome());
        comercio.setUsuario(existingUser.get());
        comercio.setFacebook(comercioRequestDTO.getFacebook());
        comercio.setInstagram(comercioRequestDTO.getInstagram());
        comercio.setTipo(tipoComercioRepository.findByNome(comercioRequestDTO.getTipo()));
        comercio.setWhatsapp(comercioRequestDTO.getWhatsapp());
        comercio.setDataCriacao(LocalDateTime.now());
        comercio.setImagem(imagemUploadResponse.urlAcesso());

        comercio = comercioRepository.save(comercio);
        return new ComercioResponseDTO(comercio.getId(), comercio.getUsuario().getId(), comercio.getNome(), comercio.getTipo().getNome(), comercio.getInstagram(), comercio.getFacebook(), comercio.getWhatsapp(), comercio.getImagem());
    }
}
