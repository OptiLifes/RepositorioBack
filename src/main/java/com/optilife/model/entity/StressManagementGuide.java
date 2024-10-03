package com.optilife.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "guide", uniqueConstraints = {
        @UniqueConstraint(columnNames = "title")
})
public class StressManagementGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 60)
    private String title;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(name = "cread_at", nullable = false)
    private LocalDateTime creadAt;
}