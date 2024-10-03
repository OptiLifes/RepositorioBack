package com.optilife.api;

import com.optilife.model.dto.VideoDTO;
import com.optilife.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping
    public ResponseEntity<VideoDTO> addVideo(@RequestBody VideoDTO videoDTO) {
        VideoDTO createdVideo = videoService.addVideo(videoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVideo);
    }

    @GetMapping
    public ResponseEntity<List<VideoDTO>> getAllFreeVideos() {
        List<VideoDTO> videos = videoService.getAllFreeVideos();
        return ResponseEntity.ok(videos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDTO> getVideoById(@PathVariable Long id) {
        VideoDTO videoDTO = videoService.getVideoById(id);
        return videoDTO != null ? ResponseEntity.ok(videoDTO) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoDTO> updateVideo(@PathVariable Long id, @RequestBody VideoDTO videoDTO) {
        VideoDTO updatedVideo = videoService.updateVideo(id, videoDTO);
        return updatedVideo != null ? ResponseEntity.ok(updatedVideo) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {
        return videoService.deleteVideo(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<VideoDTO>> searchVideosByTitle(@RequestParam String title) {
        List<VideoDTO> videos = videoService.searchVideosByTitle(title);
        return ResponseEntity.ok(videos);
    }

    @GetMapping("/search/description")
    public ResponseEntity<List<VideoDTO>> searchVideosByDescription(@RequestParam String description) {
        List<VideoDTO> videos = videoService.searchVideosByDescription(description);
        return ResponseEntity.ok(videos);
    }

}