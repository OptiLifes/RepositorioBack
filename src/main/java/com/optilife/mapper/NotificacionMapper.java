package com.optilife.mapper;

import com.optilife.model.dto.NotificacionDTO;
import com.optilife.model.entity.Notificacion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotificacionMapper {
    NotificacionMapper INSTANCE = Mappers.getMapper(NotificacionMapper.class);

    // Convierte de entidad Notificación a DTO Notificación
    NotificacionDTO toNotificacionDTO(Notificacion notificacion);

    // Convierte de DTO Notificación a entidad Notificación (si es necesario)
    Notificacion toNotificacion(NotificacionDTO notificacionDTO);
}
