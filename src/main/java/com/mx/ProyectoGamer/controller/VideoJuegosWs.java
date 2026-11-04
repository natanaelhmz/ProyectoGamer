package com.mx.ProyectoGamer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ProyectoGamer.entity.VideoJuegos;
import com.mx.ProyectoGamer.service.VideoJuegosServImp;

@RestController
@RequestMapping (path = "gamer/juegos/")
@CrossOrigin
public class VideoJuegosWs {

	@Autowired
	VideoJuegosServImp videoJuegosImp;
	
	// http://localhost:9000/gamer/juegos/mostrar
	@GetMapping(path = "mostrar")
	public List<VideoJuegos> mostrar(){
		return videoJuegosImp.mostrar();
	}
	
	//
	// http://localhost:9000/gamer/juegos/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody VideoJuegos videoJuegos){
		try {
			String response =  videoJuegosImp.guardar(videoJuegos);
			
			if(response.equals("NombreYaExiste"))
				return new ResponseEntity<String>("Ese nombre de video juego ya existe", HttpStatus.OK);
			else if (response.equals("idCategoriaNoExiste"))
				return new ResponseEntity<String>("Ese idCategoria no existe", HttpStatus.OK);
			else
				return new ResponseEntity<VideoJuegos>(videoJuegos, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error al guardar " + e.getMessage(), HttpStatus.OK);
		}
	}
	
	// http://localhost:9000/gamer/juegos/idJuego
	@GetMapping(path = "{idJuego}")
	public VideoJuegos buscar(@PathVariable ("idJuego") Long idJuego) {
		return videoJuegosImp.buscar(idJuego);
	}
	
	// http://localhost:9000/gamer/juegos/editar
	@PostMapping(path = "editar")
	public ResponseEntity<?> editar(@RequestBody VideoJuegos videoJuegos){
		try {
			String response = videoJuegosImp.editar(videoJuegos);
			
			if(response.equals("idCategoriaNoExiste"))
				return new ResponseEntity<String>("El id de la categoria no existe", HttpStatus.OK);
			else if(response.equals("idVideoJuegoNoExiste"))
				return new ResponseEntity<String>("El id del video juego no existe", HttpStatus.OK);
			else
				return new ResponseEntity<VideoJuegos>(videoJuegos, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error al editar", HttpStatus.OK);	
		}
	}
	
	// http://localhost:9000/gamer/juegos/idEiminar
	@DeleteMapping (path = "{idEliminar}")
	public ResponseEntity<?> eliminar(@PathVariable("idEliminar") Long idEliminar){
		try {
			boolean response = videoJuegosImp.eliminar(idEliminar);
			
			if(response == false)
				return new ResponseEntity<String>("No existe ese registro para eliminar", HttpStatus.OK);
			else
				return new ResponseEntity<String>("Se elimino correctamente", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error al eliminar " + e.getMessage(), HttpStatus.OK);
		}
	}
}
