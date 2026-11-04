package com.mx.ProyectoGamer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "VIDEO_JUEGOS")
@Setter
@Getter
public class VideoJuegos {
	
	@Id
	@GeneratedValue (strategy =  GenerationType.IDENTITY)
	@Column (name = "ID_JUEGO")
	private Long idJuego;
	
	@Column (name = "NOMBRE")
	private String nombre;
	
	@Column (name = "PRECIO")
	private Float precio;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CATEGORIA")
	private Genero genero;
	
	
}
