package com.optilife.model.dto;

import lombok.Data;

@Data
public class MetaDTO {
    private Integer idMeta;
    private String tipoMeta;
    private String descripcionMeta;
    private Integer objetivoTotal;
    private Double litrosAgua; // Para metas de hidratación
    private Integer horasSueño;
    private Double progresoDiario;

    // Nuevos campos para metas de alimentación
    private Integer calorias;
    private Integer proteinas;
    private Integer grasas;
    private Integer carbohidratos;
}