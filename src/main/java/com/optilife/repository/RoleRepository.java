package com.optilife.repository;

import com.optilife.model.entity.Role;
import com.optilife.model.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(ERole name);
}
