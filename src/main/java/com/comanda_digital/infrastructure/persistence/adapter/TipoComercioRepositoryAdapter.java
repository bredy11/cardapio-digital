package com.comanda_digital.infrastructure.persistence.adapter;

import com.comanda_digital.application.port.TipoComercioRepositoryPort;
import com.comanda_digital.domain.model.TipoComercio;
import com.comanda_digital.infrastructure.persistence.repository.TipoComercioJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TipoComercioRepositoryAdapter implements TipoComercioRepositoryPort {

    private final TipoComercioJpaRepository tipoComercioJpaRepository;

    public TipoComercioRepositoryAdapter(TipoComercioJpaRepository tipoComercioJpaRepository) {
        this.tipoComercioJpaRepository = tipoComercioJpaRepository;
    }

    @Override
    public Optional<TipoComercio> findById(Long id) {
        return tipoComercioJpaRepository.findById(id);
    }
}