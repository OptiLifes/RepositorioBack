package com.optilife.api;

import com.optilife.model.dto.MetaDTO;
import com.optilife.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metas")
public class MetaController {

    @Autowired
    private MetaService metaService;

    @PostMapping
    public MetaDTO registrarMeta(@RequestBody MetaDTO metaDTO) {
        return metaService.registrarMeta(metaDTO);
    }

    @GetMapping("/{perfilId}")
    public List<MetaDTO> obtenerMetasPorPerfil(@PathVariable Integer perfilId) {
        return metaService.obtenerMetasPorPerfil(perfilId);
    }
    @PutMapping("/{idMeta}/descripcion")
    public MetaDTO actualizarDescripcionMeta(@PathVariable Integer idMeta, @RequestBody String nuevaDescripcion) {
        return metaService.actualizarDescripcionMeta(idMeta, nuevaDescripcion);
    }
    // Endpoint para registrar metas de alimentación
    @PostMapping("/alimentacion")
    public MetaDTO registrarMetaAlimentacion(@RequestBody MetaDTO metaDTO) {
        return metaService.registrarMetaAlimentacion(metaDTO);
    }
}
