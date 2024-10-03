package com.optilife.api;

import com.optilife.model.dto.StressManagementGuideDTO;
import com.optilife.service.StressManagementGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guides")
public class GuideController {

    private final StressManagementGuideService guideService;

    @Autowired
    public GuideController(StressManagementGuideService guideService) {
        this.guideService = guideService;
    }

    @GetMapping
    public ResponseEntity<List<StressManagementGuideDTO>> getAllGuides() {
        List<StressManagementGuideDTO> guides = guideService.getAllGuides();
        return ResponseEntity.ok(guides);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StressManagementGuideDTO> getGuideById(@PathVariable Long id) {
        StressManagementGuideDTO guide = guideService.getGuideById(id);
        return ResponseEntity.ok(guide);
    }

    @PostMapping
    public ResponseEntity<StressManagementGuideDTO> createGuide(@RequestBody StressManagementGuideDTO guideDTO) {
        StressManagementGuideDTO createdGuide = guideService.createGuide(guideDTO);
        return ResponseEntity.status(201).body(createdGuide);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StressManagementGuideDTO> updateGuide(@PathVariable Long id, @RequestBody StressManagementGuideDTO guideDTO) {
        StressManagementGuideDTO updatedGuide = guideService.updateGuide(id, guideDTO);
        return ResponseEntity.ok(updatedGuide);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuide(@PathVariable Long id) {
        guideService.deleteGuide(id);
        return ResponseEntity.noContent().build(); // Devuelve un 204 sin contenido
    }
}