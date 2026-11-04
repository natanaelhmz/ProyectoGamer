package com.mx.ProyectoGamer.repository;

import org.springframework.data.repository.CrudRepository;

import com.mx.ProyectoGamer.entity.VideoJuegos;

public interface VideoJuegosRepository extends CrudRepository<VideoJuegos, Long>{

	public VideoJuegos findByNombre (String nombre);
}
