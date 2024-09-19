package com.optilife.mapper;

import com.optilife.model.dto.AlimentoDTO;
import com.optilife.model.entity.Alimento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlimentoMapper {
    Alimento toEntity(AlimentoDTO dto);
    AlimentoDTO toDTO(Alimento entity);
}
