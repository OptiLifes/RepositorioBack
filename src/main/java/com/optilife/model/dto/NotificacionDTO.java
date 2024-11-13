package com.optilife.model.dto;

import com.optilife.model.enums.TipoNotificacion;

public class NotificacionDTO {
    private TipoNotificacion tipoNotificacion;

    public NotificacionDTO(TipoNotificacion tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public TipoNotificacion getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(TipoNotificacion tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }
}
