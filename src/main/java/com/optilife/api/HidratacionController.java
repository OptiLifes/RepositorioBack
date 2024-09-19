package com.optilife.api;

import com.optilife.model.dto.HidratacionDTO;
import com.optilife.service.HidratacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hidratacion")
public class HidratacionController {

    @Autowired
    private HidratacionService hidratacionService;

    @PostMapping
    public HidratacionDTO registrarHidratacion(@RequestBody HidratacionDTO hidratacionDTO) {
        return hidratacionService.registrarHidratacion(hidratacionDTO);
    }
}
