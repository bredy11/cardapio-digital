package com.comanda_digital.application.port;

import com.comanda_digital.application.dto.CardapioRequestDTO;
import com.comanda_digital.application.dto.CardapioResponseDTO;

import java.util.List;

public interface CardapioServicePort {
    CardapioResponseDTO createCardapio(CardapioRequestDTO cardapioRequestDTO);
    CardapioResponseDTO getCardapioById(Long id);
    List<CardapioResponseDTO> getAllCardapios();
    CardapioResponseDTO updateCardapio(Long id, CardapioRequestDTO cardapioRequestDTO);
    void deleteCardapio(Long id);
    List<CardapioResponseDTO> getCardapiosByComercio(Long comercioId);
}