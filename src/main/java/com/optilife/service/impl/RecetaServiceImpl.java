package com.optilife.service.impl;

import com.optilife.model.entity.Receta;
import com.optilife.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaServiceImpl {

    @Autowired
    private RecetaRepository recetaRepository;

    public List<Receta> obtenerRecetas() {
        return recetaRepository.findAll();
    }

    public Receta obtenerRecetaPorId(Integer id) {
        return recetaRepository.findById(id).orElse(null);
    }

    public Receta agregarReceta(Receta receta) {
        return recetaRepository.save(receta);
    }

    public void eliminarReceta(Integer id) {
        recetaRepository.deleteById(id);
    }
}

