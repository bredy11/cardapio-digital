package com.comanda_digital.infrastructure.persistence.repository;

import com.comanda_digital.domain.model.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardapioJpaRepository extends JpaRepository<Cardapio, Long> {
    List<Cardapio> findByComercioId(Long comercioId);
}