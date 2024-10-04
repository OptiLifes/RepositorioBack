package com.optilife.service.impl;

import com.optilife.mapper.GuideMapper;
import com.optilife.model.dto.StressManagementGuideDTO;
import com.optilife.model.entity.StressManagementGuide;
import com.optilife.repository.GuideRepository;
import com.optilife.service.StressManagementGuideService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StressManagementGuideServiceImpl implements StressManagementGuideService {

    private final GuideRepository guideRepository;
    private final GuideMapper guideMapper;

    public StressManagementGuideServiceImpl(GuideRepository guideRepository, GuideMapper guideMapper) {
        this.guideRepository = guideRepository;
        this.guideMapper = guideMapper;
    }

    @Override
    public StressManagementGuideDTO createGuide(StressManagementGuideDTO guideDTO) {
        StressManagementGuide guide = guideMapper.toEntity(guideDTO);
        guide.setCreadAt(LocalDateTime.now()); // Establece la fecha de creación
        StressManagementGuide savedGuide = guideRepository.save(guide);
        return guideMapper.toDTO(savedGuide);
    }

    @Override
    public StressManagementGuideDTO getGuideById(Long id) {
        Optional<StressManagementGuide> guide = guideRepository.findById(id);
        return guide.map(guideMapper::toDTO).orElse(null);
    }

    @Override
    public List<StressManagementGuideDTO> getAllGuides() {
        return guideRepository.findAll()
                .stream()
                .map(guideMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StressManagementGuideDTO updateGuide(Long id, StressManagementGuideDTO guideDTO) {
        Optional<StressManagementGuide> existingGuide = guideRepository.findById(id);
        if (existingGuide.isPresent()) {
            StressManagementGuide guideToUpdate = guideMapper.toEntity(guideDTO);
            guideToUpdate.setId(id); // Mantén el ID original
            StressManagementGuide updatedGuide = guideRepository.save(guideToUpdate);
            return guideMapper.toDTO(updatedGuide);
        }
        return null; // O lanza una excepción
    }

    @Override
    public void deleteGuide(Long id) {
        guideRepository.deleteById(id);
    }
}
