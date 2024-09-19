package com.optilife.service;

import com.optilife.model.dto.MetaDTO;
import java.util.List;
import java.util.Map;

public interface MetaService {
    MetaDTO registrarMeta(MetaDTO metaDTO);
    List<MetaDTO> obtenerMetasPorPerfil(Integer perfilId);

    MetaDTO actualizarDescripcionMeta(Integer idMeta, String nuevaDescripcion);

    // Actualizar o registrar meta de alimentación
    MetaDTO registrarMetaAlimentacion(MetaDTO metaDTO);

    List<MetaDTO> obtenerMetasActivas(Integer perfilId);

    Map<String, Object> generarReporteProgreso(Integer perfilId, Integer idMeta, String rangoTiempo);

    Map<String, Integer> obtenerMetasCumplidasVsNoCumplidas(Integer perfilId);

}