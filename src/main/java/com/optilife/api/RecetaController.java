package com.optilife.api;

import com.optilife.model.entity.Receta;
import com.optilife.service.impl.RecetaServiceImpl; // Importa la implementación del servicio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    @Autowired
    private RecetaServiceImpl recetaService; // Cambiado para usar RecetaServiceImpl

    @GetMapping
    public List<Receta> obtenerRecetas() {
        return recetaService.obtenerRecetas();
    }

    @GetMapping("/{id}")
    public Receta obtenerRecetaPorId(@PathVariable Integer id) {
        return recetaService.obtenerRecetaPorId(id);
    }

    @PostMapping
    public Receta agregarReceta(@RequestBody Receta receta) {
        return recetaService.agregarReceta(receta);
    }

    @DeleteMapping("/{id}")
    public void eliminarReceta(@PathVariable Integer id) {
        recetaService.eliminarReceta(id);
    }
}
