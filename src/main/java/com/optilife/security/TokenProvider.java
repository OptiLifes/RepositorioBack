package com.optilife.security;

import com.optilife.model.entity.Usuario;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Component
public class TokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.validity-in-seconds}")
    private Long jwtValidityInSeconds;

    // Generación del token JWT
    public String generarToken(Usuario usuario) {
        String idUsuario = usuario.getIdUsuario().toString();
        return Jwts.builder()
                .setSubject(idUsuario)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(jwtValidityInSeconds, ChronoUnit.SECONDS)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token);
            logger.info("Token válido");
            return true;
        } catch (ExpiredJwtException e){
            logger.warn("Token expirado");
            return false;
        } catch (JwtException e){
            logger.error("Token inválido", e);
            return false;
        }
    }

    public String obtenerIdUsuario(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}