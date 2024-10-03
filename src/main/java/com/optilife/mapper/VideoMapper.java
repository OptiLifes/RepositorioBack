package com.optilife.mapper;

import com.optilife.model.dto.VideoDTO;
import com.optilife.model.entity.Video;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoMapper {
    Video toEntity(VideoDTO dto);
    VideoDTO toDTO(Video entity);
}