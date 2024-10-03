package com.optilife.repository;

import com.optilife.model.entity.StressManagementGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuideRepository extends JpaRepository<StressManagementGuide, Long> {

    List<StressManagementGuide> findByTitleContaining(String title);
    List<StressManagementGuide> findByDescriptionContaining(String description);
}