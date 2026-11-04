package com.mx.ProyectoGamer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ProyectoGamer.entity.Genero;
import com.mx.ProyectoGamer.entity.VideoJuegos;
import com.mx.ProyectoGamer.repository.GeneroRepository;
import com.mx.ProyectoGamer.repository.VideoJuegosRepository;

@Service
public class VideoJuegosServImp {

	@Autowired
	VideoJuegosRepository videoJuegosRepository;
	
	@Autowired
	GeneroRepository generoRepository;
	
	@Transactional (readOnly = true)
	public List<VideoJuegos> mostrar(){
		return (List<VideoJuegos>) videoJuegosRepository.findAll();
	}
	
	@Transactional
	public String guardar(VideoJuegos videoJuego) {
		Genero generoEnco = generoRepository.findById(videoJuego.getGenero().getIdCategoria()).orElse(null);
		
		if(generoEnco != null) {
			if(videoJuegosRepository.findByNombre(videoJuego.getNombre()) != null)
				return "NombreYaExiste";
			else {
				videoJuegosRepository.save(videoJuego);
				return "Guardado";
			}	
		}
		else
			return "idCategoriaNoExiste";
	}
	
	@Transactional (readOnly = true)
	public VideoJuegos buscar(Long idJuego) {
		return videoJuegosRepository.findById(idJuego).orElse(null);
	}
	
	@Transactional
	public String editar(VideoJuegos videoJuego) {
		Genero generoEnco =  generoRepository.findById(videoJuego.getGenero().getIdCategoria()).orElse(null);
		VideoJuegos videoJuegoEnco = buscar(videoJuego.getIdJuego());
		
		if(generoEnco != null) {
			if(videoJuegoEnco != null) {
				videoJuegosRepository.save(videoJuego);
				return "Editado";
			}else
				return "idVideoJuegoNoExiste";
		}else
			return "idCategoriaNoExiste";
	}
	
	@Transactional
	public boolean eliminar(Long idEliminar) {
		VideoJuegos videoJuegoEnco = buscar(idEliminar);
		
		if(videoJuegoEnco == null)
			return false;
		else {
			videoJuegosRepository.deleteById(idEliminar);
			return true;
		}
	}
	
}
