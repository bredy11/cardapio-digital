package com.comanda_digital.application.port;

import com.comanda_digital.application.dto.restaurante.ComercioRequestDTO;
import com.comanda_digital.application.dto.restaurante.ComercioResponseDTO;
import com.comanda_digital.domain.model.Comercio;

public interface ComercioServicePort {

    ComercioResponseDTO cadastrarComercio(ComercioRequestDTO comercioRequestDTO);

}
