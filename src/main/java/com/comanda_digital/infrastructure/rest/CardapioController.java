package com.comanda_digital.infrastructure.rest;

import com.comanda_digital.application.dto.CardapioRequestDTO;
import com.comanda_digital.application.dto.CardapioResponseDTO;
import com.comanda_digital.application.port.CardapioServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cardapios")
public class CardapioController {

    private final CardapioServicePort cardapioService;

    public CardapioController(CardapioServicePort cardapioService) {
        this.cardapioService = cardapioService;
    }

    @PostMapping
    public ResponseEntity<CardapioResponseDTO> createCardapio(@RequestBody CardapioRequestDTO requestDTO) {
        CardapioResponseDTO responseDTO = cardapioService.createCardapio(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardapioResponseDTO> getCardapioById(@PathVariable Long id) {
        CardapioResponseDTO responseDTO = cardapioService.getCardapioById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<CardapioResponseDTO>> getAllCardapios(@RequestParam(required = false) Long comercioId) {
        List<CardapioResponseDTO> cardapios;
        if (comercioId != null) {
            cardapios = cardapioService.getCardapiosByComercio(comercioId);
        } else {
            cardapios = cardapioService.getAllCardapios();
        }
        return ResponseEntity.ok(cardapios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardapioResponseDTO> updateCardapio(@PathVariable Long id, @RequestBody CardapioRequestDTO requestDTO) {
        CardapioResponseDTO updatedCardapio = cardapioService.updateCardapio(id, requestDTO);
        return ResponseEntity.ok(updatedCardapio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCardapio(@PathVariable Long id) {
        cardapioService.deleteCardapio(id);
        return ResponseEntity.noContent().build();
    }
}