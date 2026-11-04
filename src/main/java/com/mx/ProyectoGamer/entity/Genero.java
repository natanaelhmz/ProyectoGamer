package com.mx.ProyectoGamer.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "GENERO")
@Getter
@Setter
public class Genero {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CATEGORIA")
	private Long idCategoria;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@OneToMany (mappedBy = "genero", cascade = CascadeType.ALL)
	@JsonIgnore
	public List<VideoJuegos> lista = new ArrayList<>();
}
