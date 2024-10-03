package com.optilife.api;

import com.optilife.model.dto.MetaDTO;
import com.optilife.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/metas")
public class MetaController {

    @Autowired
    private MetaService metaService;

    @PostMapping
    public MetaDTO registrarMeta(@RequestBody MetaDTO metaDTO) {
        return metaService.registrarMeta(metaDTO);
    }

    @PostMapping("/sueño")
    public MetaDTO registrarMetaSueño(@RequestBody MetaDTO metaDTO) {
        return metaService.registrarMetaSueño(metaDTO);
    }
    @PostMapping("/Alimento")
    public MetaDTO registrarAlimento(@RequestBody MetaDTO metaDTO) {
        return metaService.registrarMetaAlimentacion(metaDTO);
    }

    @GetMapping("/{perfilId}")
    public List<MetaDTO> obtenerMetasPorPerfil(@PathVariable Integer perfilId) {
        return metaService.obtenerMetasPorPerfil(perfilId);
    }

    @PutMapping("/{idMeta}/descripcion")
    public MetaDTO actualizarDescripcionMeta(@PathVariable Integer idMeta, @RequestBody String nuevaDescripcion) {
        return metaService.actualizarDescripcionMeta(idMeta, nuevaDescripcion);
    }

    // Endpoint para eliminar una meta
    @DeleteMapping("/{idMeta}")
    public void eliminarMeta(@PathVariable Integer idMeta) {
        metaService.eliminarMeta(idMeta);
    }

    // Nuevo endpoint para obtener todas las metas activas de un perfil
    @GetMapping("/activas/{perfilId}")
    public List<MetaDTO> obtenerMetasActivas(@PathVariable Integer perfilId) {
        return metaService.obtenerMetasActivas(perfilId);
    }

    // Nuevo endpoint para generar reportes
    @GetMapping("/reportes")
    public Map<String, Object> generarReporteProgreso(
            @RequestParam Integer perfilId,
            @RequestParam Integer idMeta,
            @RequestParam String rangoTiempo) {
        return metaService.generarReporteProgreso(perfilId, idMeta, rangoTiempo);
    }

    @GetMapping("/reportes/cumplidas-vs-no-cumplidas")
    public Map<String, Integer> obtenerMetasCumplidasVsNoCumplidas(@RequestParam Integer perfilId) {
        return metaService.obtenerMetasCumplidasVsNoCumplidas(perfilId);
    }
}