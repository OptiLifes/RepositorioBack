package com.optilife.repository;

import com.optilife.model.entity.Token;
import com.optilife.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    default void saveTokenForUser(@Param("token") String token, @Param("usuario") Usuario usuario) {
        Token tokenEntity = new Token();
        tokenEntity.setToken(token);
        tokenEntity.setUsuario(usuario);
        save(tokenEntity);//guardar un token junto con el usuario
    }

    //@Query("SELECT t.usuario FROM Token t JOIN FETCH t.usuario WHERE t.token = :token")
    //Optional<Usuario> findUserByToken(@Param("token") String token);

    Optional<Token> findByToken(String token);
}





