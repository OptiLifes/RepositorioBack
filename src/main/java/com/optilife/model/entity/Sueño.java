package com.optilife.model.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sueño")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sueño {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sueno")
    private Integer idSueño;

    @Column(name = "horas_dormidas", nullable = false)
    private String horasDormidas; // Podría ser mejor como Double o Integer dependiendo de la precisión requerida

    // Relaciones

    @ManyToOne
    @JoinColumn(name = "id_salud", nullable = false)
    private Salud salud;
}
