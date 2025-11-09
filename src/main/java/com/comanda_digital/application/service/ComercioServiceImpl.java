package com.comanda_digital.application.service;

import com.comanda_digital.application.dto.restaurante.AtualizarImagemRequestDTO;
import com.comanda_digital.application.dto.restaurante.ComercioRequestDTO;
import com.comanda_digital.application.dto.restaurante.ComercioResponseDTO;
import com.comanda_digital.application.mapper.ComercioMapper;
import com.comanda_digital.application.port.ComercioServicePort;
import com.comanda_digital.domain.model.Comercio;
import com.comanda_digital.domain.model.Usuario;
import com.comanda_digital.infrastructure.cloud.ImagemClient;
import com.comanda_digital.application.dto.endereco.AtualizarEnderecoRequestDTO;
import com.comanda_digital.domain.model.Endereco;
import com.comanda_digital.infrastructure.cloud.record.ImagemUploadRequest;
import com.comanda_digital.infrastructure.cloud.record.ImagemUploadResponse;
import com.comanda_digital.infrastructure.persistence.ComercioRepository;
import com.comanda_digital.infrastructure.persistence.EnderecoRepository;
import com.comanda_digital.infrastructure.persistence.TipoComercioRepository;
import com.comanda_digital.infrastructure.persistence.UsuarioRepository;
import com.comanda_digital.infrastructure.specification.ComercioSpecification;
import com.comanda_digital.shared.exception.EmailAlreadyExistsException;
import com.comanda_digital.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ComercioServiceImpl implements ComercioServicePort {

    private final ComercioRepository comercioRepository;
    private final UsuarioRepository usuarioRepository;
    private final TipoComercioRepository tipoComercioRepository;
    private final ImagemClient imagemClient;
    private final EnderecoRepository enderecoRepository;
    private final String urlStorage;

    private final ComercioMapper comercioMapper;

    public ComercioServiceImpl(ComercioRepository comercioRepository, UsuarioRepository usuarioRepository, TipoComercioRepository tipoComercioRepository,
                               ImagemClient imagemClient, EnderecoRepository enderecoRepository, @Value("${cardapio.digital.storage.url}") String urlStorage,
                               ComercioMapper comercioMapper) {
        this.comercioRepository = comercioRepository;
        this.usuarioRepository = usuarioRepository;
        this.tipoComercioRepository = tipoComercioRepository;
        this.imagemClient = imagemClient;
        this.enderecoRepository = enderecoRepository;
        this.urlStorage = urlStorage;
        this.comercioMapper = comercioMapper;
    }

    @Override
    public ComercioResponseDTO cadastrarComercio(ComercioRequestDTO comercioRequestDTO) {
        Optional<Usuario> existingUser = usuarioRepository.findById(comercioRequestDTO.getUsuarioId());

        if (!existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Usuario não localizado");
        }
        ImagemUploadRequest imagemUploadRequest = new ImagemUploadRequest(comercioRequestDTO.getImagemBase64(), comercioRequestDTO.getUsuarioId());
        ImagemUploadResponse imagemUploadResponse = imagemClient.uploadImagem(imagemUploadRequest);

        Comercio comercio = new Comercio();
        comercio.setNome(comercioRequestDTO.getNome());
        comercio.setUsuario(existingUser.get());
        comercio.setFacebook(comercioRequestDTO.getFacebook());
        comercio.setInstagram(comercioRequestDTO.getInstagram());
        comercio.setTipo(tipoComercioRepository.findByNome(comercioRequestDTO.getTipo()));
        comercio.setWhatsapp(comercioRequestDTO.getWhatsapp());
        comercio.setDataCriacao(LocalDateTime.now());
        comercio.setUrlImagem(imagemUploadResponse.urlAcesso());

        comercio = comercioRepository.save(comercio);
        return comercioMapper.toComercioResponseDTO(comercio, urlStorage);
    }

    @Override
    public void atualizarEndereco(Long comercioId, AtualizarEnderecoRequestDTO atualizarEnderecoRequestDTO) {
        Comercio comercio = comercioRepository.findById(comercioId)
                .orElseThrow(() -> new ResourceNotFoundException("Comercio não encontrado"));

        Endereco endereco = comercio.getEndereco();
        if (endereco == null) {
            endereco = new Endereco();
            endereco.setComercio(comercio);
        }

        endereco.setRua(atualizarEnderecoRequestDTO.getRua());
        endereco.setNumero(atualizarEnderecoRequestDTO.getNumero());
        endereco.setBairro(atualizarEnderecoRequestDTO.getBairro());
        endereco.setCidade(atualizarEnderecoRequestDTO.getCidade());
        endereco.setEstado(atualizarEnderecoRequestDTO.getEstado());
        endereco.setCep(atualizarEnderecoRequestDTO.getCep());

        enderecoRepository.save(endereco);
    }

    @Override
    public java.util.List<ComercioResponseDTO> listarComercios(String nome, String tipo) {
        Specification<Comercio> spec = Specification.where(null);

        if (nome != null && !nome.isEmpty()) {
            spec = spec.and(ComercioSpecification.likeNome(nome));
        }

        if (tipo != null && !tipo.isEmpty()) {
            spec = spec.and(ComercioSpecification.equalTipo(tipo));
        }

        return comercioRepository.findAll(spec).stream()
                .map(comercio -> comercioMapper.toComercioResponseDTO(comercio, urlStorage))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public void atualizarImagem(Long comercioId, AtualizarImagemRequestDTO atualizarImagemRequestDTO) {
        Comercio comercio = comercioRepository.findById(comercioId)
                .orElseThrow(() -> new ResourceNotFoundException("Comercio não encontrado"));

        ImagemUploadRequest imagemUploadRequest = new ImagemUploadRequest(atualizarImagemRequestDTO.getImagemBase64(), comercio.getUsuario().getId());
        ImagemUploadResponse imagemUploadResponse = imagemClient.uploadImagem(imagemUploadRequest);

        comercio.setUrlImagem(imagemUploadResponse.urlAcesso());
        comercioRepository.save(comercio);
    }
}
