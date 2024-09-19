package com.optilife.repository;

import com.optilife.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Método para buscar un usuario por su correo electrónico
    Usuario findByEmail(String email);

    // Método para verificar si un correo ya está registrado
    boolean existsByEmail(String email);
}
