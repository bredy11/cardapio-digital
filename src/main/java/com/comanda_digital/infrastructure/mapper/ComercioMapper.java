package com.comanda_digital.infrastructure.mapper;

import com.comanda_digital.application.dto.ComercioRequestDTO;
import com.comanda_digital.application.dto.ComercioResponseDTO;
import com.comanda_digital.domain.model.Comercio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ComercioMapper {

    ComercioMapper INSTANCE = Mappers.getMapper(ComercioMapper.class);

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "tipo.id", target = "tipoId")
    @Mapping(source = "tipo.nome", target = "tipoNome")
    ComercioResponseDTO toComercioResponseDTO(Comercio comercio);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "tipo", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    Comercio toComercio(ComercioRequestDTO comercioRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "tipo", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    void updateComercioFromDTO(ComercioRequestDTO dto, @MappingTarget Comercio comercio);
}