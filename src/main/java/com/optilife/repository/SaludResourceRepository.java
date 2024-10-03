package com.optilife.repository;
import com.optilife.model.entity.SaludResource;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface SaludResourceRepository extends JpaRepository<SaludResource, Long> {
    List<SaludResource> findByCategoria(String categoria);
}