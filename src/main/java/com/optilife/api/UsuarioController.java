package com.optilife.api;

import com.optilife.model.dto.UsuarioActualizacionDTO;
import com.optilife.model.dto.UsuarioPerfilDTO;
import com.optilife.model.dto.UsuarioRegistroDTO;
import com.optilife.model.dto.UsuarioLoginDTO;
import com.optilife.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Registro de usuario
    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@Valid @RequestBody UsuarioRegistroDTO usuarioRegistroDTO) {
        try {
            usuarioService.registrarUsuario(usuarioRegistroDTO);
            return ResponseEntity.ok("Usuario registrado con éxito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar usuario: " + e.getMessage());
        }
    }

    // Inicio de sesión
    @PostMapping("/login")
    public ResponseEntity<String> iniciarSesion(@RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        try {
            boolean sesionValida = usuarioService.validarCredenciales(usuarioLoginDTO);

            if (sesionValida) {
                return ResponseEntity.ok("Inicio de sesión exitoso. Redirigiendo al dashboard...");
            } else {
                return ResponseEntity.status(401).body("Credenciales inválidas. Por favor, intente nuevamente.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error durante el inicio de sesión: " + e.getMessage());
        }
    }

    // Obtener perfil del usuario
    @GetMapping("/perfil")
    public ResponseEntity<UsuarioPerfilDTO> obtenerPerfilUsuario(Authentication authentication) {
        try {
            String email = authentication.getName();
            UsuarioPerfilDTO perfilDTO = usuarioService.obtenerPerfilPorEmail(email);
            return ResponseEntity.ok(perfilDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // Actualización de perfil y metas de salud del usuario
    @PutMapping("/perfil")
    public ResponseEntity<String> actualizarPerfilUsuario(
            Authentication authentication,
            @Valid @RequestBody UsuarioActualizacionDTO usuarioActualizacionDTO) {
        try {
            String email = authentication.getName();
            usuarioService.actualizarPerfilUsuario(email, usuarioActualizacionDTO);
            return ResponseEntity.ok("Perfil y metas actualizados con éxito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar el perfil: " + e.getMessage());
        }
    }

    // Cierre de sesión
    @PostMapping("/logout")
    public ResponseEntity<String> cerrarSesion(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {
            request.getSession().invalidate(); // Invalida la sesión
            return ResponseEntity.ok("Sesión cerrada con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al cerrar sesión: " + e.getMessage());
        }
    }

    // Actualización de la foto de perfil del usuario
    @PostMapping("/perfil/foto")
    public ResponseEntity<String> actualizarFotoPerfil(
            @RequestParam("foto") MultipartFile foto,
            Authentication authentication) {
        try {
            String email = authentication.getName(); // Obtener el email del usuario autenticado

            // Llamar al servicio para actualizar la foto de perfil
            usuarioService.actualizarFotoPerfil(email, foto);

            return ResponseEntity.ok("Foto de perfil actualizada con éxito.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar la foto de perfil: " + e.getMessage());
        }
    }
}
