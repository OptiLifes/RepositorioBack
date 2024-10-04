package com.optilife.api;

import com.optilife.model.entity.Recurso;
import com.optilife.service.impl.RecursoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recursos")
public class RecursoController {

    @Autowired
    private RecursoServiceImpl recursoService;

    // Endpoint para obtener todos los recursos
    @GetMapping
    public List<Recurso> obtenerTodosLosRecursos() {
        return recursoService.obtenerTodosLosRecursos();
    }

    // Endpoint para buscar recursos por título
    @GetMapping("/buscar")
    public List<Recurso> buscarPorTitulo(@RequestParam String keyword) {
        return recursoService.buscarPorTitulo(keyword);
    }

    // Endpoint para filtrar recursos por categoría
    @GetMapping("/filtrar")
    public List<Recurso> filtrarPorCategoria(@RequestParam String categoria) {
        return recursoService.buscarPorCategoria(categoria);
    }
}
