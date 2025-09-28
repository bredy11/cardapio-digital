package com.comanda_digital.infrastructure.persistence;

import com.comanda_digital.domain.model.TipoComercio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoComercioRepository extends JpaRepository<TipoComercio, Long> {
}