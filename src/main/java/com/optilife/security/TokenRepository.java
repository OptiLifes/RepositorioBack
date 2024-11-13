package com.optilife.security;

import com.optilife.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    // Encontrar un token específico
    Optional<Token> findByToken(String token);

    // Recuperar un usuario en base al token
    Optional<Token> findUserByToken(@Param("token") String token);

}





