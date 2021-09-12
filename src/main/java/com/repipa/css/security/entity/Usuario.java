package com.repipa.css.security.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.repipa.css.security.entity.Rol;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USUARIO")
public class Usuario {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String email;
	private String direccion;
	@NonNull
	@ManyToMany
	@JoinTable(name = "USUARIO_ROL", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_rol"))
	private Set<Rol> roles = new HashSet<>();


	@NonNull
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
    private Date lastLogging;
    private Date lastUpdate;

    public Usuario(@NonNull String nombre, @NonNull String email,@NonNull String direccion, @NonNull String password){
		this.nombre = nombre;
		this.email = email;
		this.direccion = direccion;
		this.password = password;
	}
}
