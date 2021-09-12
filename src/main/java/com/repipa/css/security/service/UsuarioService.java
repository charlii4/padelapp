package com.repipa.css.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repipa.css.security.repository.UsuarioRepository;
import com.repipa.css.security.entity.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository uRep;
	
	public List<Usuario> getUsuarios(){
		return uRep.findAll();
	}
	
	public Usuario saveUsuario(Usuario u) {
		return uRep.save(u);
	}
	
	public String deleteUsuario(int id) {
		uRep.deleteById(id);

		return "Eliminado usuario " + id;
	}
	
	public Usuario updateUsuario(Usuario u) {
		//Usuario existingUsuario = uRep.findById(u.getId()).orElse(null);
		Usuario existingUsuario = uRep.findById(u.getId());
		existingUsuario.setNombre(u.getNombre());
		existingUsuario.setEmail(u.getEmail());
		existingUsuario.setDireccion(u.getDireccion());

		return uRep.save(u);
	}

	public Optional<Usuario> getByUsuario(String nombre){
		return uRep.findByEmail(nombre);
	}

/*	public Boolean existsByUsuario(String nombreUsuario){
		return uRep.existsByNombreUsuario(nombreUsuario);
	}*/

	public Optional<Usuario> findByEmail(String email) { return uRep.findByEmail(email);}

	public Boolean existsByEmail(String email){
		return uRep.existsByEmail(email);
	}

}
