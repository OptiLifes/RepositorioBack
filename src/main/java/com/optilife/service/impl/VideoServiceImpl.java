package com.optilife.service.impl;

import com.optilife.model.dto.VideoDTO;
import com.optilife.model.entity.Video;
import com.optilife.repository.VideoRepository;
import com.optilife.mapper.VideoMapper;
import com.optilife.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public VideoDTO addVideo(VideoDTO videoDTO) {
        Video video = videoMapper.toEntity(videoDTO);
        return videoMapper.toDTO(videoRepository.save(video));
    }

    @Override
    public List<VideoDTO> getAllFreeVideos() {
        return videoRepository.findAll().stream()
                .map(videoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VideoDTO getVideoById(Long id) {
        return videoRepository.findById(id)
                .map(videoMapper::toDTO)
                .orElse(null);
    }

    @Override
    public VideoDTO updateVideo(Long id, VideoDTO videoDTO) {
        Video videoToUpdate = videoRepository.findById(id)
                .orElse(null);
        if (videoToUpdate != null) {
            videoToUpdate.setTitulo(videoDTO.getTitulo());
            videoToUpdate.setDescription(videoDTO.getDescription());
            videoToUpdate.setUrl(videoDTO.getUrl());
            videoToUpdate.setMiniaturaUrl(videoDTO.getMiniaturaUrl());
            return videoMapper.toDTO(videoRepository.save(videoToUpdate));
        }
        return null;
    }

    @Override
    public boolean deleteVideo(Long id) {
        if (videoRepository.existsById(id)) {
            videoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<VideoDTO> searchVideosByTitle(String title) {
        return videoRepository.findByTituloContaining(title).stream()
                .map(videoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VideoDTO> searchVideosByDescription(String description) {
        return videoRepository.findByDescriptionContaining(description).stream()
                .map(videoMapper::toDTO)
                .collect(Collectors.toList());
    }
}

