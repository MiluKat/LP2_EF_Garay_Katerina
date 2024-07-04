package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "tb_usuario")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UsuarioEntity {

	@Id
	@Column(name = "usuario_id", nullable = false, unique = true)
	private String correo;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "celular", nullable = false)
	private String celular;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "urlImagen", nullable = false)
	private String urlImagen;
	

}
