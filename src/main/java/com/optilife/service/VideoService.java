package com.optilife.service;

import com.optilife.model.dto.VideoDTO;

import java.util.List;

public interface VideoService {
    VideoDTO addVideo(VideoDTO videoDTO);
    List<VideoDTO> getAllFreeVideos();
    VideoDTO getVideoById(Long id);
    VideoDTO updateVideo(Long id, VideoDTO videoDTO);
    boolean deleteVideo(Long id);
    List<VideoDTO> searchVideosByTitle(String title); // Método para buscar videos por título
    List<VideoDTO> searchVideosByDescription(String description); // Método para buscar videos por descripción
}
