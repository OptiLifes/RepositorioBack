package com.optilife.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "alimento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alimento")
    private Integer idAlimento;

    @Column(name = "nombre_alimento", nullable = false)
    private String nombre;

    @Column(name = "calorias", nullable = false)
    private Integer calorias;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private String categoria; // desayuno, almuerzo, cena

    // Relación con la entidad Salud
    @ManyToOne
    @JoinColumn(name = "id_salud", nullable = false)
    private Salud salud;
}
