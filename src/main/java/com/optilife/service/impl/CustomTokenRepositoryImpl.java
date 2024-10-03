package com.optilife.service.impl;

import com.optilife.model.entity.Usuario;
import com.optilife.repository.CustomTokenRepository;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class CustomTokenRepositoryImpl implements CustomTokenRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void saveTokenForUser(String token, Usuario usuario) {
        String query = "UPDATE Token t SET t.usuario = :usuario WHERE t.token = :token";
        entityManager.createQuery(query)
                .setParameter("usuario", usuario)
                .setParameter("token", token)
                .executeUpdate();
    }
}


