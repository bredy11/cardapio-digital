package com.comanda_digital.infrastructure.mapper;

import com.comanda_digital.application.dto.CardapioRequestDTO;
import com.comanda_digital.application.dto.CardapioResponseDTO;
import com.comanda_digital.domain.model.Cardapio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CardapioMapper {

    CardapioMapper INSTANCE = Mappers.getMapper(CardapioMapper.class);

    @Mapping(source = "comercio.id", target = "comercioId")
    CardapioResponseDTO toCardapioResponseDTO(Cardapio cardapio);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comercio", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    Cardapio toCardapio(CardapioRequestDTO cardapioRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comercio", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    void updateCardapioFromDTO(CardapioRequestDTO dto, @MappingTarget Cardapio cardapio);
}