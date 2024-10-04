package com.optilife.repository;

import com.optilife.model.entity.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer> {
    // Puedes añadir métodos de búsqueda personalizados aquí si es necesario
}

