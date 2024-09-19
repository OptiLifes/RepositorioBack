package com.optilife.model.dto;

import lombok.Data;

@Data
public class AlimentoDTO {
    private Integer idAlimento;
    private String nombre;
    private Integer calorias;
    private Integer cantidad;
    private String categoria;
}
