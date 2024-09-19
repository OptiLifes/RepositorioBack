package com.optilife.service;

import com.optilife.model.dto.AlimentoDTO;
import java.util.List;

public interface AlimentoService {
    AlimentoDTO registrarAlimento(AlimentoDTO alimentoDTO);
    List<AlimentoDTO> obtenerAlimentosPorSalud(Integer saludId);
}
