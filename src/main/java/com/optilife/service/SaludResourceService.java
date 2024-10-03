package com.optilife.service;
import com.optilife.model.entity.SaludResource;
import java.util.List;
public interface SaludResourceService {
    List<SaludResource> obtenerRecursosPorCategoria(String categoria);
    SaludResource obtenerRecursoPorId(Long id);
}