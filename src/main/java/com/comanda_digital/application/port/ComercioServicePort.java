package com.comanda_digital.application.port;

import com.comanda_digital.application.dto.ComercioRequestDTO;
import com.comanda_digital.application.dto.ComercioResponseDTO;

import java.util.List;

public interface ComercioServicePort {
    ComercioResponseDTO createComercio(ComercioRequestDTO comercioRequestDTO);
    ComercioResponseDTO getComercioById(Long id);
    List<ComercioResponseDTO> getAllComercios();
    ComercioResponseDTO updateComercio(Long id, ComercioRequestDTO comercioRequestDTO);
    void deleteComercio(Long id);
}