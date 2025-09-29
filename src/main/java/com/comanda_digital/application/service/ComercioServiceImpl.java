package com.comanda_digital.application.service;

import com.comanda_digital.application.dto.ComercioRequestDTO;
import com.comanda_digital.application.dto.ComercioResponseDTO;
import com.comanda_digital.application.port.ComercioRepositoryPort;
import com.comanda_digital.application.port.ComercioServicePort;
import com.comanda_digital.application.port.TipoComercioRepositoryPort;
import com.comanda_digital.application.port.UsuarioRepositoryPort;
import com.comanda_digital.domain.model.Comercio;
import com.comanda_digital.domain.model.TipoComercio;
import com.comanda_digital.domain.model.Usuario;
import com.comanda_digital.infrastructure.mapper.ComercioMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComercioServiceImpl implements ComercioServicePort {

    private final ComercioRepositoryPort comercioRepository;
    private final UsuarioRepositoryPort usuarioRepository;
    private final TipoComercioRepositoryPort tipoComercioRepository;
    private final ComercioMapper comercioMapper;

    public ComercioServiceImpl(
            ComercioRepositoryPort comercioRepository,
            UsuarioRepositoryPort usuarioRepository,
            TipoComercioRepositoryPort tipoComercioRepository,
            ComercioMapper comercioMapper) {
        this.comercioRepository = comercioRepository;
        this.usuarioRepository = usuarioRepository;
        this.tipoComercioRepository = tipoComercioRepository;
        this.comercioMapper = comercioMapper;
    }

    @Override
    @Transactional
    public ComercioResponseDTO createComercio(ComercioRequestDTO comercioRequestDTO) {
        Comercio comercio = comercioMapper.toComercio(comercioRequestDTO);

        Usuario usuario = usuarioRepository.findById(comercioRequestDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o id: " + comercioRequestDTO.getUsuarioId()));

        TipoComercio tipoComercio = tipoComercioRepository.findById(comercioRequestDTO.getTipoId())
                .orElseThrow(() -> new RuntimeException("Tipo de Comércio não encontrado com o id: " + comercioRequestDTO.getTipoId()));

        comercio.setUsuario(usuario);
        comercio.setTipo(tipoComercio);

        Comercio savedComercio = comercioRepository.save(comercio);
        return comercioMapper.toComercioResponseDTO(savedComercio);
    }

    @Override
    @Transactional(readOnly = true)
    public ComercioResponseDTO getComercioById(Long id) {
        Comercio comercio = comercioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comércio não encontrado com o id: " + id));
        return comercioMapper.toComercioResponseDTO(comercio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComercioResponseDTO> getAllComercios() {
        return comercioRepository.findAll().stream()
                .map(comercioMapper::toComercioResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ComercioResponseDTO updateComercio(Long id, ComercioRequestDTO comercioRequestDTO) {
        Comercio existingComercio = comercioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comércio não encontrado com o id: " + id));

        comercioMapper.updateComercioFromDTO(comercioRequestDTO, existingComercio);

        if (comercioRequestDTO.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(comercioRequestDTO.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o id: " + comercioRequestDTO.getUsuarioId()));
            existingComercio.setUsuario(usuario);
        }

        if (comercioRequestDTO.getTipoId() != null) {
            TipoComercio tipoComercio = tipoComercioRepository.findById(comercioRequestDTO.getTipoId())
                    .orElseThrow(() -> new RuntimeException("Tipo de Comércio não encontrado com o id: " + comercioRequestDTO.getTipoId()));
            existingComercio.setTipo(tipoComercio);
        }

        Comercio updatedComercio = comercioRepository.save(existingComercio);
        return comercioMapper.toComercioResponseDTO(updatedComercio);
    }

    @Override
    @Transactional
    public void deleteComercio(Long id) {
        if (!comercioRepository.findById(id).isPresent()) {
            throw new RuntimeException("Comércio não encontrado com o id: " + id);
        }
        comercioRepository.deleteById(id);
    }
}