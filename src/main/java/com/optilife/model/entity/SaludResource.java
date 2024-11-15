package com.optilife.model.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "salud_resource")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaludResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String categoria;
    @Column(nullable = false)
    private String contenido;

}