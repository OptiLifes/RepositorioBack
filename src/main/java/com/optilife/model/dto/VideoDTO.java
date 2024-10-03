package com.optilife.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VideoDTO {
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 60, message = "El título debe tener 60 carácteres o menos")
    private String titulo;

    @NotBlank(message = "La descripcion es obligatorio")
    @Size(max = 100, message = "La descripcion debe tener 100 caracteres o menos")
    private String description;

    @NotBlank(message = "La url es obligatoria")
    @Size(max = 255, message = "La URL debe tener 255 caracteres o menos")
    private String url;


    private String miniaturaUrl;
}