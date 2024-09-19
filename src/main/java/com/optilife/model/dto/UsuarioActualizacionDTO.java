package com.optilife.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioActualizacionDTO {
    private String nombre;
    private String email;
    private String metasSalud; // Metas relacionadas con la salud
}