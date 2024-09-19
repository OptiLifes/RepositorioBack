package com.optilife.mapper;

import com.optilife.model.dto.MetaDTO;
import com.optilife.model.entity.Meta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MetaMapper {
    Meta toEntity(MetaDTO dto);
    MetaDTO toDTO(Meta entity);
}
