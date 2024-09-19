package com.optilife.service;

import com.optilife.model.entity.Notificacion;
import java.util.List;

public interface NotificacionService {

    // Obtener las notificaciones configuradas del usuario
    List<Notificacion> obtenerNotificaciones(Integer usuarioId) throws Exception;

    // Actualizar las preferencias de notificación del usuario
    void actualizarPreferenciasNotificacion(Integer usuarioId, List<String> tiposNotificacion) throws Exception;
}
