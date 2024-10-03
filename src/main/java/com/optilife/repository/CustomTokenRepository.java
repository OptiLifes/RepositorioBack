package com.optilife.repository;

public interface CustomTokenRepository {
    void saveTokenForUser(String token, com.optilife.model.entity.Usuario usuario);
}


