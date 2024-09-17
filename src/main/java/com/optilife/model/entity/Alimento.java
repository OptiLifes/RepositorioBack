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

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer calorias;

    // Relaciones

    @ManyToOne
    @JoinColumn(name = "salud_id", nullable = false)
    private Salud salud;
}
