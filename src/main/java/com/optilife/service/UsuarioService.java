package com.optilife.service;

import com.optilife.model.dto.*;
import org.springframework.web.multipart.MultipartFile;

public interface UsuarioService {
    void registrarUsuario(UsuarioRegistroDTO usuarioRegistroDTO) throws Exception;

    boolean validarCredenciales(UsuarioLoginDTO usuarioLoginDTO) throws Exception;

    UsuarioPerfilDTO obtenerPerfilPorEmail(String email);

    AuthResponseDTO iniciarSesion(UsuarioLoginDTO usuarioLoginDTO) throws Exception;

    void actualizarPerfilUsuario(String email, UsuarioActualizacionDTO usuarioActualizacionDTO) throws Exception;

    void actualizarFotoPerfil(String email, MultipartFile foto) throws Exception;

    void generarTokenRecuperacion(String email) throws Exception;

    void restablecerContraseña(String token, String nuevaContraseña) throws Exception;
}
