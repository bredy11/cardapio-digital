package com.comanda_digital.application.port;

import com.comanda_digital.domain.model.Comercio;

import java.util.List;
import java.util.Optional;

public interface ComercioRepositoryPort {
    Comercio save(Comercio comercio);
    Optional<Comercio> findById(Long id);
    List<Comercio> findAll();
    void deleteById(Long id);
}