package com.comanda_digital.application.service;

import com.comanda_digital.application.dto.CardapioRequestDTO;
import com.comanda_digital.application.dto.CardapioResponseDTO;
import com.comanda_digital.application.port.CardapioRepositoryPort;
import com.comanda_digital.application.port.CardapioServicePort;
import com.comanda_digital.application.port.ComercioRepositoryPort;
import com.comanda_digital.domain.model.Cardapio;
import com.comanda_digital.domain.model.Comercio;
import com.comanda_digital.infrastructure.mapper.CardapioMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardapioServiceImpl implements CardapioServicePort {

    private final CardapioRepositoryPort cardapioRepository;
    private final ComercioRepositoryPort comercioRepository;
    private final CardapioMapper cardapioMapper;

    public CardapioServiceImpl(
            CardapioRepositoryPort cardapioRepository,
            ComercioRepositoryPort comercioRepository,
            CardapioMapper cardapioMapper) {
        this.cardapioRepository = cardapioRepository;
        this.comercioRepository = comercioRepository;
        this.cardapioMapper = cardapioMapper;
    }

    @Override
    @Transactional
    public CardapioResponseDTO createCardapio(CardapioRequestDTO cardapioRequestDTO) {
        Cardapio cardapio = cardapioMapper.toCardapio(cardapioRequestDTO);

        Comercio comercio = comercioRepository.findById(cardapioRequestDTO.getComercioId())
                .orElseThrow(() -> new RuntimeException("Comércio não encontrado com o id: " + cardapioRequestDTO.getComercioId()));

        cardapio.setComercio(comercio);

        Cardapio savedCardapio = cardapioRepository.save(cardapio);
        return cardapioMapper.toCardapioResponseDTO(savedCardapio);
    }

    @Override
    @Transactional(readOnly = true)
    public CardapioResponseDTO getCardapioById(Long id) {
        Cardapio cardapio = cardapioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cardápio não encontrado com o id: " + id));
        return cardapioMapper.toCardapioResponseDTO(cardapio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CardapioResponseDTO> getAllCardapios() {
        return cardapioRepository.findAll().stream()
                .map(cardapioMapper::toCardapioResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CardapioResponseDTO updateCardapio(Long id, CardapioRequestDTO cardapioRequestDTO) {
        Cardapio existingCardapio = cardapioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cardápio não encontrado com o id: " + id));

        cardapioMapper.updateCardapioFromDTO(cardapioRequestDTO, existingCardapio);

        if (cardapioRequestDTO.getComercioId() != null) {
            Comercio comercio = comercioRepository.findById(cardapioRequestDTO.getComercioId())
                    .orElseThrow(() -> new RuntimeException("Comércio não encontrado com o id: " + cardapioRequestDTO.getComercioId()));
            existingCardapio.setComercio(comercio);
        }

        Cardapio updatedCardapio = cardapioRepository.save(existingCardapio);
        return cardapioMapper.toCardapioResponseDTO(updatedCardapio);
    }

    @Override
    @Transactional
    public void deleteCardapio(Long id) {
        if (!cardapioRepository.findById(id).isPresent()) {
            throw new RuntimeException("Cardápio não encontrado com o id: " + id);
        }
        cardapioRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CardapioResponseDTO> getCardapiosByComercio(Long comercioId) {
        return cardapioRepository.findByComercioId(comercioId).stream()
                .map(cardapioMapper::toCardapioResponseDTO)
                .collect(Collectors.toList());
    }
}