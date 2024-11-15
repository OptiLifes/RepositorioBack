package com.optilife.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 50)
    private String titulo;

    @Column(name = "description",nullable = false, length = 1000)
    private String description;

    @Column(name = "url", nullable = false, unique = true)
    private String url;//una url unica (?

    @Column(nullable = false)
    private String miniaturaUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private Recurso recurso;
}