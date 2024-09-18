package com.optilife.model.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sueno")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sueno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sueno")
    private Integer idSueno;

    @Column(name = "horas_dormidas", nullable = false)
    private String horasDormidas; // Podría ser mejor como Double o Integer dependiendo de la precisión requerida

    // Relaciones

    @ManyToOne
    @JoinColumn(name = "salud_id", nullable = false)
    private Salud salud;
}
