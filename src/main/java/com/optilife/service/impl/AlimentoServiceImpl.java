package com.optilife.service.impl;

import com.optilife.model.dto.AlimentoDTO;
import com.optilife.model.entity.Alimento;
import com.optilife.repository.AlimentoRepository;
import com.optilife.mapper.AlimentoMapper;
import com.optilife.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlimentoServiceImpl implements AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;

    @Autowired
    private AlimentoMapper alimentoMapper;

    @Override
    public AlimentoDTO registrarAlimento(AlimentoDTO alimentoDTO) {
        Alimento alimento = alimentoMapper.toEntity(alimentoDTO);
        return alimentoMapper.toDTO(alimentoRepository.save(alimento));
    }

    @Override
    public List<AlimentoDTO> obtenerAlimentosPorSalud(Integer saludId) {
        return alimentoRepository.findAll().stream()
                .filter(alimento -> alimento.getSalud().getIdSalud().equals(saludId))
                .map(alimentoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
