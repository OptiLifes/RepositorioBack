package com.optilife.repository;

import com.optilife.model.entity.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {

    // Accede al idUsuario dentro de la entidad Usuario
    List<Notificacion> findByUsuario_IdUsuario(Integer idUsuario);

    // Para eliminar notificaciones por usuario
    void deleteByUsuario_IdUsuario(Integer idUsuario);
}
