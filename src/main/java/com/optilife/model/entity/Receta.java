package com.optilife.model.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(length = 2000) // Para descripciones largas
    private String descripcion;

    private String categoria;
    private Integer calorias;
    private Integer carbohidratos;
    private Integer grasas;
    private Integer proteinas;

    @ElementCollection
    private List<String> ingredientes;

    @ElementCollection
    private List<String> restriccionesAlimentarias; // Ej. Sin gluten, vegano, etc.

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Getters y Setters

    // Método para verificar si una receta cumple con una restricción
    public boolean cumpleRestricciones(List<String> restriccionesUsuario) {
        for (String restriccion : restriccionesUsuario) {
            if (this.restriccionesAlimentarias.contains(restriccion)) {
                return true;
            }
        }
        return false;
    }
}
