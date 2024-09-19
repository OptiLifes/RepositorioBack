package com.optilife.api;

import com.optilife.model.dto.AlimentoDTO;
import com.optilife.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alimentos")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @PostMapping
    public AlimentoDTO registrarAlimento(@RequestBody AlimentoDTO alimentoDTO) {
        return alimentoService.registrarAlimento(alimentoDTO);
    }

    @GetMapping("/{saludId}")
    public List<AlimentoDTO> obtenerAlimentos(@PathVariable Integer saludId) {
        return alimentoService.obtenerAlimentosPorSalud(saludId);
    }
}
