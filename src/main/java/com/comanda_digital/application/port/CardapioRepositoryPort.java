package com.comanda_digital.application.port;

import com.comanda_digital.domain.model.Cardapio;

import java.util.List;
import java.util.Optional;

public interface CardapioRepositoryPort {
    Cardapio save(Cardapio cardapio);
    Optional<Cardapio> findById(Long id);
    List<Cardapio> findAll();
    void deleteById(Long id);
    List<Cardapio> findByComercioId(Long comercioId);
}