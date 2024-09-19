package com.optilife.repository;

import com.optilife.model.entity.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MetaRepository extends JpaRepository<Meta, Integer> {
    List<Meta> findByPerfil_IdPerfil(Integer idPerfil);
}
