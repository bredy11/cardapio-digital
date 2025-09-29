package com.comanda_digital.infrastructure.persistence.adapter;

import com.comanda_digital.application.port.CardapioRepositoryPort;
import com.comanda_digital.domain.model.Cardapio;
import com.comanda_digital.infrastructure.persistence.repository.CardapioJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CardapioRepositoryAdapter implements CardapioRepositoryPort {

    private final CardapioJpaRepository cardapioJpaRepository;

    public CardapioRepositoryAdapter(CardapioJpaRepository cardapioJpaRepository) {
        this.cardapioJpaRepository = cardapioJpaRepository;
    }

    @Override
    public Cardapio save(Cardapio cardapio) {
        return cardapioJpaRepository.save(cardapio);
    }

    @Override
    public Optional<Cardapio> findById(Long id) {
        return cardapioJpaRepository.findById(id);
    }

    @Override
    public List<Cardapio> findAll() {
        return cardapioJpaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        cardapioJpaRepository.deleteById(id);
    }

    @Override
    public List<Cardapio> findByComercioId(Long comercioId) {
        return cardapioJpaRepository.findByComercioId(comercioId);
    }
}