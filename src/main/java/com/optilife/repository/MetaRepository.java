package com.optilife.repository;

import com.optilife.model.entity.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaRepository extends JpaRepository<Meta, Integer> {
    // Aquí puedes agregar consultas personalizadas si es necesario
}
