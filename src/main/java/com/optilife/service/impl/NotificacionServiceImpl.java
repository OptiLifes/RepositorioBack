package com.optilife.service.impl;

import com.optilife.enums.TipoNotificacion;
import com.optilife.model.entity.Notificacion;
import com.optilife.model.entity.Usuario;
import com.optilife.repository.NotificacionRepository;
import com.optilife.repository.UsuarioRepository;
import com.optilife.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Notificacion> obtenerNotificaciones(Integer usuarioId) throws Exception {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
        return notificacionRepository.findByUsuario_IdUsuario(usuarioId);
    }

    @Override
    public void actualizarPreferenciasNotificacion(Integer usuarioId, List<String> tiposNotificacion) throws Exception {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        // Eliminar las notificaciones existentes
        notificacionRepository.deleteByUsuario_IdUsuario(usuarioId);

        // Crear nuevas notificaciones según las preferencias
        for (String tipo : tiposNotificacion) {
            Notificacion notificacion = new Notificacion();
            notificacion.setUsuario(usuario);
            notificacion.setTipoNotificacion(TipoNotificacion.valueOf(tipo));  // Usa el setter correcto con el enum
            notificacion.setMensaje("Notificación de tipo: " + tipo);
            notificacionRepository.save(notificacion);
        }
    }
}
