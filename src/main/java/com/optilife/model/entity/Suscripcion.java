package com.optilife.model.entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "suscripcion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"usuario", "recursos"})
@ToString(exclude = {"usuario", "recursos"})
public class Suscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suscripcion")
    private Integer idSuscripcion;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime fechaFin;

    // Relaciones
    @OneToOne
    @JoinColumn(name = "id_usuario", unique = true)
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pago", unique = true)
    private Pago pago;

    @OneToMany(mappedBy = "suscripcion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Recurso> recursos;
}
