package com.optilife.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hidratacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hidratacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hidratacion")
    private Integer idHidratacion;

    @Column(name = "cantidad_agua", nullable = false)
    private Integer cantidadAgua; // En mililitros

    // Relación con la entidad Salud
    @ManyToOne
    @JoinColumn(name = "salud_id", nullable = false)
    private Salud salud;
}
