package com.repipa.css.security.repository;

import com.repipa.css.security.entity.Rol;
import com.repipa.css.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByNombre(RolNombre rolNombre);
}
