package com.optilife.service;

import com.optilife.model.dto.StressManagementGuideDTO;
import java.util.List;

public interface StressManagementGuideService {
    StressManagementGuideDTO createGuide(StressManagementGuideDTO guideDTO);
    StressManagementGuideDTO getGuideById(Long id);
    List<StressManagementGuideDTO> getAllGuides();
    StressManagementGuideDTO updateGuide(Long id, StressManagementGuideDTO guideDTO);
    void deleteGuide(Long id);
}
