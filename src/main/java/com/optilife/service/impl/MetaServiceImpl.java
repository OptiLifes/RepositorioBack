package com.optilife.service.impl;

import com.optilife.model.dto.MetaDTO;
import com.optilife.model.entity.Meta;
import com.optilife.repository.MetaRepository;
import com.optilife.mapper.MetaMapper;
import com.optilife.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<MetaDTO> obtenerMetasPorPerfil(Integer perfilId) {
        return metaRepository.findByPerfil_IdPerfil(perfilId).stream()
                .map(metaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MetaDTO actualizarDescripcionMeta(Integer idMeta, String nuevaDescripcion) {
        // Validar que la descripción no sea vacía
        if (nuevaDescripcion == null || nuevaDescripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }

        // Buscar la meta por ID
        Meta meta = metaRepository.findById(idMeta)
                .orElseThrow(() -> new IllegalArgumentException("Meta no encontrada"));

        // Actualizar la descripción de la meta
        meta.setDescripcionMeta(nuevaDescripcion);

        // Guardar la meta actualizada
        Meta metaActualizada = metaRepository.save(meta);

        // Retornar la meta actualizada como DTO
        return metaMapper.toDTO(metaActualizada);
    }

    // Implementación para metas de alimentación
    @Override
    public MetaDTO registrarMetaAlimentacion(MetaDTO metaDTO) {
        // Validaciones para los campos de la meta de alimentación
        if (metaDTO.getCalorias() == null || metaDTO.getProteinas() == null ||
                metaDTO.getGrasas() == null || metaDTO.getCarbohidratos() == null) {
            throw new IllegalArgumentException("Todos los campos nutricionales son obligatorios");
        }

        // Crear o actualizar la meta de alimentación
        Meta meta = metaMapper.toEntity(metaDTO);
        meta.setTipoMeta("Alimentación");

        // Guardar la meta en la base de datos
        return metaMapper.toDTO(metaRepository.save(meta));
    }
}
