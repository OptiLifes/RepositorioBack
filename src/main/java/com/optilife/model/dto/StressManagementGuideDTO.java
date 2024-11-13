package com.optilife.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StressManagementGuideDTO {
    private Long id;

    @NotBlank(message = "El titulo es obligatorio")
    @Size(max = 60, message = "El título debe tener 60 caracteres o menoss")
    private String title;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(max = 1000, message = "La descripcion debe tener 1000 caracteres o menos")
    private String description;

    @NotBlank(message = "El contenido es obligatorio")
    private String content;
}