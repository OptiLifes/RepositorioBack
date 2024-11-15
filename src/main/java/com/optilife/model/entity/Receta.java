package com.optilife.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recetas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receta")
    private Integer idReceta;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "tipo_receta", nullable = false)
    private String tipoReceta; // Ejemplo: "Vegetariana", "Keto", "Postre", etc.

    @Column(name = "categoria")
    private String categoria; // Ejemplo: "Desayuno", "Almuerzo", "Cena", etc.

    // Relaciones

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario; // Usuario que crea la receta

    @ManyToOne
    @JoinColumn(name = "id_suscripcion", nullable = false)
    private Suscripcion suscripcion; // Suscripción que permite acceso a la receta
}

