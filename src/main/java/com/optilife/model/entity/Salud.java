package com.optilife.model.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "salud")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_salud")
    private Integer idSalud;

    // Relaciones

    @OneToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;

    @OneToMany(mappedBy = "salud", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Alimento> alimentos;

    @OneToMany(mappedBy = "salud", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Hidratacion> hidrataciones;

    @OneToMany(mappedBy = "salud", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sueño> suenos;
}