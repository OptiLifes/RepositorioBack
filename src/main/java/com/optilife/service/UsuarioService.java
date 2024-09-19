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

    // Método para actualizar el perfil del usuario y sus metas
    void actualizarPerfilUsuario(String email, UsuarioActualizacionDTO usuarioActualizacionDTO) throws Exception;

    // Método para actualizar la foto de perfil
    void actualizarFotoPerfil(String email, MultipartFile foto) throws Exception;
}
