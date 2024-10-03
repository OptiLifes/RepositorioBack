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

    // Campo específico para la meta de hidratación
    @Column(name = "litros_agua", nullable = true)
    private Double litrosAgua; // Litros o mililitros de agua diarios como meta

    @Column(name = "progreso_diario", nullable = true)
    private Double progresoDiario; // Guardar el progreso diario

    //Campo para Meta de sueño
    @Column(name = "horas_sueño", nullable = true)
    private Integer horasSueño;

    // Campos para metas de alimentación
    @Column(name = "calorias", nullable = true)
    private Integer calorias;

    @Column(name = "proteinas", nullable = true)
    private Integer proteinas;

    @Column(name = "grasas", nullable = true)
    private Integer grasas;

    @Column(name = "carbohidratos", nullable = true)
    private Integer carbohidratos;

    // Relación con el perfil del usuario
    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;
}