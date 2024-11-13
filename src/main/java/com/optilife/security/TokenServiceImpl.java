package com.optilife.security;

import com.optilife.model.entity.Token;
import com.optilife.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public String generarToken(Usuario usuario) {
        //generando token JWT a través de TokenProvider
        String token = tokenProvider.generarToken(usuario);

        //crear y guardar token en la DB con expiración
        Token newToken = new Token(token, usuario, LocalDateTime.now().plusHours(24));
        tokenRepository.save(newToken);
        return token;
    }

    @Override
    public Optional<Usuario> validarToken(String token) {
        Optional<Token> optionalToken = tokenRepository.findByToken(token);
        if (optionalToken.isPresent() && tokenProvider.validarToken(token)) {
            Token tokenEntity = optionalToken.get();

            //verificando si el token expiró o no
            if (tokenEntity.getFechaExpiracion().isAfter(LocalDateTime.now())) {
                return Optional.of(tokenEntity.getUsuario());
            } else {
                tokenRepository.delete(tokenEntity);
            }
        }
        return Optional.empty();
    }
}