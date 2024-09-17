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
    private String tipoMeta;

    @Column(name = "descripcion_meta", nullable = false)
    private String descripcionMeta;

    // Relaciones

    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;
}