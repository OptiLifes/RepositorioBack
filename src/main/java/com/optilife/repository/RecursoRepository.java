package com.optilife.repository;

import com.optilife.model.entity.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Long> {

    // Búsqueda por título
    List<Recurso> findByTituloContainingIgnoreCase(String titulo);

    // Búsqueda por categoría
    List<Recurso> findByCategoria(String categoria);


}

