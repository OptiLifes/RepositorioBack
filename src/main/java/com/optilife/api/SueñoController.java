package com.optilife.api;

import com.optilife.model.dto.SueñoDTO;
import com.optilife.service.SueñoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sueño")
public class SueñoController {

    @Autowired
    private SueñoService sueñoService;

    @PostMapping
    public SueñoDTO registrarSueño(@RequestBody SueñoDTO sueñoDTO) {
        return sueñoService.registrarSueño(sueñoDTO);
    }
}
