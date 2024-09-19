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
    private String tipoMeta; // Ej. "Actividad física", "Dieta", "Manejo de estrés"

    @Column(name = "descripcion_meta", nullable = false)
    private String descripcionMeta; // Descripción de la meta

    @Column(name = "objetivo_total", nullable = false)
    private Integer objetivoTotal; // Ej. minutos de ejercicio, calorías diarias, etc.

    // Nuevos campos para las metas de alimentación
    @Column(name = "calorias", nullable = true)
    private Integer calorias;

    @Column(name = "proteinas", nullable = true)
    private Integer proteinas;

    @Column(name = "grasas", nullable = true)
    private Integer grasas;

    @Column(name = "carbohidratos", nullable = true)
    private Integer carbohidratos;

    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;
}
