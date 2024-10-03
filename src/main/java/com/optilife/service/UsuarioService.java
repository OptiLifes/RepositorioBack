package com.optilife.service;

import com.optilife.model.dto.UsuarioActualizacionDTO;
import com.optilife.model.dto.UsuarioLoginDTO;
import com.optilife.model.dto.UsuarioRegistroDTO;
import com.optilife.model.dto.UsuarioPerfilDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UsuarioService {
    void registrarUsuario(UsuarioRegistroDTO usuarioRegistroDTO) throws Exception;

    boolean validarCredenciales(UsuarioLoginDTO usuarioLoginDTO) throws Exception;

    UsuarioPerfilDTO obtenerPerfilPorEmail(String email);

    void actualizarPerfilUsuario(String email, UsuarioActualizacionDTO usuarioActualizacionDTO) throws Exception;

    void actualizarFotoPerfil(String email, MultipartFile foto) throws Exception;

    // Nuevos métodos para la recuperación de contraseña
    void generarTokenRecuperacion(String email) throws Exception;

    void restablecerContraseña(String token, String nuevaContraseña) throws Exception;
}
