package com.optilife.api;

import com.optilife.model.dto.NotificacionDTO;
import com.optilife.service.NotificacionService;
import com.optilife.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping("/preferencias")
    public ResponseEntity<List<NotificacionDTO>> obtenerPreferencias(Authentication authentication) {
        try {
            Integer usuarioId = ((Usuario) authentication.getPrincipal()).getIdUsuario();  // Corregido a Integer
            List<NotificacionDTO> notificaciones = notificacionService.obtenerNotificaciones(usuarioId).stream()
                    .map(n -> new NotificacionDTO(n.getTipoNotificacion()))  // Usa el método getTipoNotificacion
                    .collect(Collectors.toList());
            return ResponseEntity.ok(notificaciones);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/preferencias")
    public ResponseEntity<String> actualizarPreferencias(Authentication authentication,
                                                         @RequestBody List<NotificacionDTO> notificacionesDTO) {
        try {
            Integer usuarioId = ((Usuario) authentication.getPrincipal()).getIdUsuario();  // Corregido a Integer
            List<String> tiposNotificacion = notificacionesDTO.stream()
                    .map(dto -> dto.getTipoNotificacion().name())  // Convierte el enum a su nombre (String)
                    .collect(Collectors.toList());

            notificacionService.actualizarPreferenciasNotificacion(usuarioId, tiposNotificacion);
            return ResponseEntity.ok("Preferencias de notificación actualizadas con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al actualizar las preferencias de notificación: " + e.getMessage());
        }
    }
}
