package com.optilife.service.impl;

import com.optilife.model.entity.Token;
import com.optilife.model.entity.Usuario;
import com.optilife.repository.TokenRepository;
import com.optilife.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public String generarToken(Usuario usuario) {
        String token = UUID.randomUUID().toString();
        Token nuevoToken = new Token(token, usuario, LocalDateTime.now().plusHours(24)); // El token expira en 24 horas
        tokenRepository.save(nuevoToken); // Guardamos el token en la base de datos
        return token;
    }

    @Override
    public Optional<Usuario> validarToken(String token) {
        return tokenRepository.findUsuarioByToken(token);
    }
}



