package com.optilife.security;

import com.optilife.model.entity.Usuario;

import java.util.Optional;


public interface TokenService {

    // Genera un token único para la recuperación de contraseña
    String generarToken(Usuario usuario);

    // Validar el token y obtener el usuario correspondiente
    Optional<Usuario> validarToken(String token);

    void saveTokenForUser(Usuario usuario, String token);

}
