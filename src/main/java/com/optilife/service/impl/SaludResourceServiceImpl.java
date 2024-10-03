package com.optilife.service.impl;
import com.optilife.model.entity.SaludResource;
import com.optilife.repository.SaludResourceRepository;
import com.optilife.service.SaludResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SaludResourceServiceImpl implements SaludResourceService {
    @Autowired
    private SaludResourceRepository saludResourceRepository;
    @Override
    public List<SaludResource> obtenerRecursosPorCategoria(String categoria) {
        return saludResourceRepository.findByCategoria(categoria);
    }
    @Override
    public SaludResource obtenerRecursoPorId(Long id) {
        return saludResourceRepository.findById(id).orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
    }
}