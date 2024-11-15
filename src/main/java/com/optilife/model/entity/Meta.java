package com.optilife.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "meta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_meta")
    private Integer idMeta;

    @Column(name = "tipo_meta", nullable = false)
    private String tipoMeta; // Ej. "Hidratación", "Actividad física", "Dieta", "Manejo de estrés", etc.

    @Column(name = "descripcion_meta", nullable = false)
    private String descripcionMeta; // Descripción de la meta

    @Column(name = "objetivo_total", nullable = false)
    private Integer objetivoTotal; // Objetivo general de la meta (ej. minutos, calorías, litros, etc.)

    @Column(name = "litros_agua", nullable = false)
    private Double litrosAgua; // Litros o mililitros de agua diarios como meta

    @Column(name = "progreso_diario", nullable = true)
    private Double progresoDiario; // Guardar el progreso diario

    //Campo para Meta de sueño
    @Column(name = "horas_sueño", nullable = false)
    private Integer horasSueño;

    // Campos para metas de alimentación
    @Column(name = "calorias", nullable = false)
    private Integer calorias;

    @Column(name = "proteinas", nullable = false)
    private Integer proteinas;

    @Column(name = "grasas", nullable = false)
    private Integer grasas;

    @Column(name = "carbohidratos", nullable = false)
    private Integer carbohidratos;

    // Relación con el perfil del usuario
    @ManyToOne
    @JoinColumn(name = "id_perfil", nullable = false)
    private Perfil perfil;
}