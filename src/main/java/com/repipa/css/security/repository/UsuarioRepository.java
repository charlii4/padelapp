package com.repipa.css.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.repipa.css.security.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Usuario findById(int id);
	Optional<Usuario> findByEmail(String email);
	Optional<Usuario> findByNombre(String nombre);
	//boolean existsByNombreUsuario (String nombreUsuario);
	boolean existsByEmail (String email);

}
