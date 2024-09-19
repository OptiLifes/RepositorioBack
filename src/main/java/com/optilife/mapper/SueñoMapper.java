package com.optilife.mapper;

import com.optilife.model.dto.SueñoDTO;
import com.optilife.model.entity.Sueño;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SueñoMapper {
    Sueño toEntity(SueñoDTO dto);
    SueñoDTO toDTO(Sueño entity);
}
