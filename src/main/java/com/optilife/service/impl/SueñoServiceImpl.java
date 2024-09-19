package com.optilife.service.impl;

import com.optilife.model.dto.SueñoDTO;
import com.optilife.model.entity.Sueño;
import com.optilife.repository.SueñoRepository;
import com.optilife.mapper.SueñoMapper;
import com.optilife.service.SueñoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SueñoServiceImpl implements SueñoService {

    @Autowired
    private SueñoRepository sueñoRepository;

    @Autowired
    private SueñoMapper sueñoMapper;

    @Override
    public SueñoDTO registrarSueño(SueñoDTO sueñoDTO) {
        Sueño sueño = sueñoMapper.toEntity(sueñoDTO);
        return sueñoMapper.toDTO(sueñoRepository.save(sueño));
    }
}
