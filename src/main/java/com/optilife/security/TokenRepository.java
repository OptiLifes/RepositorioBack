package com.optilife.security;

import com.optilife.model.entity.Token;
import com.optilife.model.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    // Encontrar un token específico
    Optional<Token> findByToken(String token);

    @Modifying
    @Transactional
    @Query("UPDATE Token t SET t.usuario = :usuario WHERE t.token = :token")
    void saveTokenForUser(@Param("token") String token, @Param("usuario") Usuario usuario);
}