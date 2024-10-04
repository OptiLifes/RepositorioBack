package com.optilife.repository;

import com.optilife.model.entity.Token;
import com.optilife.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.transaction.Transactional;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Modifying
    @Transactional
    @Query("INSERT INTO Token (token, usuario) VALUES (:token, :usuario)")
    void saveTokenForUser(@Param("token") String token, @Param("usuario") Usuario usuario);

    @Query("SELECT t.usuario FROM Token t WHERE t.token = :token")
    Optional<Usuario> findUsuarioByToken(@Param("token") String token);
}





