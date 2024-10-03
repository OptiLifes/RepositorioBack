package com.optilife.service.impl;

import com.optilife.model.dto.MetaDTO;
import com.optilife.model.entity.Meta;
import com.optilife.repository.MetaRepository;
import com.optilife.mapper.MetaMapper;
import com.optilife.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class MetaServiceImpl implements MetaService {

    @Autowired
    private MetaRepository metaRepository;

    @Autowired
    private MetaMapper metaMapper;

    @Override
    public MetaDTO registrarMeta(MetaDTO metaDTO) {
        Meta meta = metaMapper.toEntity(metaDTO);
        return metaMapper.toDTO(metaRepository.save(meta));
    }

    @Override
    public void eliminarMeta(Integer idMeta) {
        Meta meta = metaRepository.findById(idMeta)
                .orElseThrow(() -> new IllegalArgumentException("Meta no encontrada"));

        metaRepository.delete(meta);
    }

    @Override
    public MetaDTO registrarMetaSueño(MetaDTO metaDTO) {
        if (metaDTO.getHorasSueno() == null || metaDTO.getHorasSueno() <= 0) {
            throw new IllegalArgumentException("El valor de horas de sueño debe ser válido y mayor que 0");
        }

        Meta metaSueno = metaMapper.toEntity(metaDTO);
        metaSueno.setTipoMeta("Sueño");

        return metaMapper.toDTO(metaRepository.save(metaSueno));
    }

    @Override
    public List<MetaDTO> obtenerMetasPorPerfil(Integer perfilId) {
        return metaRepository.findByPerfil_IdPerfil(perfilId).stream()
                .map(metaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MetaDTO actualizarDescripcionMeta(Integer idMeta, String nuevaDescripcion) {
        if (nuevaDescripcion == null || nuevaDescripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
        Meta meta = metaRepository.findById(idMeta)
                .orElseThrow(() -> new IllegalArgumentException("Meta no encontrada"));
        meta.setDescripcionMeta(nuevaDescripcion);
        return metaMapper.toDTO(metaRepository.save(meta));
    }

    @Override
    public MetaDTO registrarMetaAlimentacion(MetaDTO metaDTO) {
        if (metaDTO.getCalorias() == null || metaDTO.getProteinas() == null ||
                metaDTO.getGrasas() == null || metaDTO.getCarbohidratos() == null) {
            throw new IllegalArgumentException("Todos los campos nutricionales son obligatorios");
        }
        Meta meta = metaMapper.toEntity(metaDTO);
        meta.setTipoMeta("Alimentación");
        return metaMapper.toDTO(metaRepository.save(meta));
    }

    @Override
    public List<MetaDTO> obtenerMetasActivas(Integer perfilId) {
        return metaRepository.findByPerfil_IdPerfil(perfilId).stream()
                .map(metaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> generarReporteProgreso(Integer perfilId, Integer idMeta, String rangoTiempo) {
        Meta meta = metaRepository.findById(idMeta)
                .orElseThrow(() -> new IllegalArgumentException("Meta no encontrada"));
        Map<String, Object> reporte = new HashMap<>();
        reporte.put("meta", metaMapper.toDTO(meta));

        if (rangoTiempo.equals("semana")) {
            reporte.put("progreso", 75);
        } else if (rangoTiempo.equals("mes")) {
            reporte.put("progreso", 85);
        }

        return reporte;
    }

    @Override
    public Map<String, Integer> obtenerMetasCumplidasVsNoCumplidas(Integer perfilId) {
        List<Meta> metas = metaRepository.findByPerfil_IdPerfil(perfilId);
        int metasCumplidas = 0;
        int metasNoCumplidas = 0;

        for (Meta meta : metas) {
            if (meta.getObjetivoTotal() != null && meta.getObjetivoTotal() > 0) {
                metasCumplidas++;
            } else {
                metasNoCumplidas++;
            }
        }

        Map<String, Integer> resultado = new HashMap<>();
        resultado.put("metasCumplidas", metasCumplidas);
        resultado.put("metasNoCumplidas", metasNoCumplidas);

        return resultado;
    }
}