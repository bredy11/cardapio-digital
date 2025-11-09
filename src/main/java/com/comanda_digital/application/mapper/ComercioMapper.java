package com.comanda_digital.application.mapper;

import com.comanda_digital.application.dto.restaurante.ComercioResponseDTO;
import com.comanda_digital.domain.model.Comercio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EnderecoMapper.class})
public interface ComercioMapper {

    @Mapping(source = "comercio.usuario.id", target = "usuarioId")
    @Mapping(source = "comercio.tipo.nome", target = "tipo")
    @Mapping(target = "urlImagem", expression = "java(urlStorage + comercio.getUrlImagem())")
    ComercioResponseDTO toComercioResponseDTO(Comercio comercio, String urlStorage);
}
