package com.optilife.mapper;

import com.optilife.model.dto.HidratacionDTO;
import com.optilife.model.entity.Hidratacion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HidratacionMapper {
    Hidratacion toEntity(HidratacionDTO dto);
    HidratacionDTO toDTO(Hidratacion entity);
}
