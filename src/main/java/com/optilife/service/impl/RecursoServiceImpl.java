package com.optilife.service.impl;

import com.optilife.model.entity.Recurso;
import com.optilife.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecursoServiceImpl {

    @Autowired
    private RecursoRepository recursoRepository;

    // Búsqueda por palabra clave en el título
    public List<Recurso> buscarPorTitulo(String keyword) {
        return recursoRepository.findByTituloContainingIgnoreCase(keyword);
    }

    // Filtrar por categoría
    public List<Recurso> buscarPorCategoria(String categoria) {
        return recursoRepository.findByCategoria(categoria);
    }

    // Obtener todos los recursos
    public List<Recurso> obtenerTodosLosRecursos() {
        return recursoRepository.findAll();
    }
}

