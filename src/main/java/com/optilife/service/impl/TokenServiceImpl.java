package com.optilife.service.impl;

import com.optilife.model.entity.Token;
import com.optilife.model.entity.Usuario;
import com.optilife.repository.TokenRepository;
import com.optilife.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public String generarToken(Usuario usuario) {
        //generator UUID unico para usuario
        String idUsuario = UUID.randomUUID().toString();
        //generator token JWT
        String token = Jwts.builder()
                .setSubject(idUsuario)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(24, ChronoUnit.HOURS)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        //creando nuevo token con el JWT y fecha de expiración
        Token nuevoToken = new Token(token,usuario,LocalDateTime.now().plusHours(24));
        tokenRepository.save(nuevoToken);//gurdando en DB
        return token;
    }

    public Optional<Usuario> validarToken(String token) {
        Optional<Token> optionalToken = tokenRepository.findByToken(token);
        if (optionalToken.isPresent()) {
            Token tokenEntity = optionalToken.get();
            if(tokenEntity.getFechaExpiracion().isAfter(LocalDateTime.now())) {
                return Optional.of(tokenEntity.getUsuario());
            }else {
                tokenRepository.delete(tokenEntity);
            }
        }
        return Optional.empty();
    }
}



