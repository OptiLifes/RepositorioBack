package com.optilife.mapper;

import com.optilife.model.dto.StressManagementGuideDTO;
import com.optilife.model.entity.StressManagementGuide;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuideMapper {
    StressManagementGuide toEntity(StressManagementGuideDTO dto);
    StressManagementGuideDTO toDTO(StressManagementGuide entity);
}