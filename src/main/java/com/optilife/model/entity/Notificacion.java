package com.optilife.model.entity;

import com.optilife.model.enums.TipoNotificacion;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notificacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private Integer idNotificacion;

    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_notificacion", nullable = false)
    private TipoNotificacion tipoNotificacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
