package com.optilife.api;
import com.optilife.model.entity.SaludResource;
import com.optilife.service.SaludResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/salud")
public class SaludResourceController {
    @Autowired
    private SaludResourceService saludResourceService;
    @GetMapping("/recursos")
    public ResponseEntity<List<SaludResource>> obtenerRecursos(@RequestParam String categoria) {
        List<SaludResource> recursos = saludResourceService.obtenerRecursosPorCategoria(categoria);
        return ResponseEntity.ok(recursos);
    }
    @GetMapping("/recurso/{id}")
    public ResponseEntity<SaludResource> obtenerRecurso(@PathVariable Long id) {
        SaludResource recurso = saludResourceService.obtenerRecursoPorId(id);
        return ResponseEntity.ok(recurso);
    }
}