package com.optilife.service.impl;

import com.optilife.model.dto.HidratacionDTO;
import com.optilife.model.entity.Hidratacion;
import com.optilife.repository.HidratacionRepository;
import com.optilife.mapper.HidratacionMapper;
import com.optilife.service.HidratacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HidratacionServiceImpl implements HidratacionService {

    @Autowired
    private HidratacionRepository hidratacionRepository;

    @Autowired
    private HidratacionMapper hidratacionMapper;

    @Override
    public HidratacionDTO registrarHidratacion(HidratacionDTO hidratacionDTO) {
        Hidratacion hidratacion = hidratacionMapper.toEntity(hidratacionDTO);
        return hidratacionMapper.toDTO(hidratacionRepository.save(hidratacion));
    }
}
