package com.optilife.service;

import com.optilife.model.dto.MetaDTO;
import java.util.List;

public interface MetaService {
    MetaDTO registrarMeta(MetaDTO metaDTO);
    List<MetaDTO> obtenerMetasPorPerfil(Integer perfilId);

    MetaDTO actualizarDescripcionMeta(Integer idMeta, String nuevaDescripcion);

    // Actualizar o registrar meta de alimentación
    MetaDTO registrarMetaAlimentacion(MetaDTO metaDTO);
}
