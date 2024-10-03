package com.optilife.repository;

import com.optilife.model.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findByTituloContaining(String titulo);
    List<Video> findByDescriptionContaining(String description);
}
