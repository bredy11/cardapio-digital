package com.comanda_digital.application.port;

import com.comanda_digital.domain.model.TipoComercio;
import java.util.Optional;

public interface TipoComercioRepositoryPort {
    Optional<TipoComercio> findById(Long id);
}