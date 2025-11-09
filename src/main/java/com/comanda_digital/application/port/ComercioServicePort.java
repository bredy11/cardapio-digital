package com.comanda_digital.application.port;

import com.comanda_digital.application.dto.endereco.AtualizarEnderecoRequestDTO;
import com.comanda_digital.application.dto.restaurante.ComercioRequestDTO;
import com.comanda_digital.application.dto.restaurante.ComercioResponseDTO;
import com.comanda_digital.domain.model.Comercio;

public interface ComercioServicePort {

    ComercioResponseDTO cadastrarComercio(ComercioRequestDTO comercioRequestDTO);

    void atualizarEndereco(Long comercioId, AtualizarEnderecoRequestDTO atualizarEnderecoRequestDTO);

    java.util.List<ComercioResponseDTO> listarComercios(String nome, String tipo);

    void atualizarImagem(Long comercioId, com.comanda_digital.application.dto.restaurante.AtualizarImagemRequestDTO atualizarImagemRequestDTO);

}
