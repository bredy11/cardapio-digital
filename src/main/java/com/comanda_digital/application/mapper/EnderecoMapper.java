package com.comanda_digital.application.mapper;

import com.comanda_digital.application.dto.endereco.EnderecoDTO;
import com.comanda_digital.domain.model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    EnderecoDTO toEnderecoDTO(Endereco endereco);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comercio", ignore = true)
    Endereco toEndereco(EnderecoDTO enderecoDTO);
}
