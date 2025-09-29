package com.comanda_digital.infrastructure.persistence.adapter;

import com.comanda_digital.application.port.ComercioRepositoryPort;
import com.comanda_digital.domain.model.Comercio;
import com.comanda_digital.infrastructure.persistence.repository.ComercioJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ComercioRepositoryAdapter implements ComercioRepositoryPort {

    private final ComercioJpaRepository comercioJpaRepository;

    public ComercioRepositoryAdapter(ComercioJpaRepository comercioJpaRepository) {
        this.comercioJpaRepository = comercioJpaRepository;
    }

    @Override
    public Comercio save(Comercio comercio) {
        return comercioJpaRepository.save(comercio);
    }

    @Override
    public Optional<Comercio> findById(Long id) {
        return comercioJpaRepository.findById(id);
    }

    @Override
    public List<Comercio> findAll() {
        return comercioJpaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        comercioJpaRepository.deleteById(id);
    }
}