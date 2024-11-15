package com.optilife.model.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recurso")
    private Integer idRecurso;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "tipo_recurso", nullable = false)
    private String tipoRecurso;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "titulo")
    private String titulo;

    // Relaciones

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_suscripcion", nullable = false)
    private Suscripcion suscripcion;
}
