package com.comanda_digital.infrastructure.persistence.repository;

import com.comanda_digital.domain.model.Comercio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComercioJpaRepository extends JpaRepository<Comercio, Long> {
}