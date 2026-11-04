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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ProyectoGamer.entity.Genero;
import com.mx.ProyectoGamer.service.GeneroServImp;

@RestController
@RequestMapping(path = "gamer/genero/")
@CrossOrigin
public class GeneroWs {
	
	@Autowired
	GeneroServImp generoServiceImp;

	// http://localhost:9000/gamer/genero/mostrar
	@GetMapping (path = "mostrar")
	public List<Genero> mostrar(){
		return generoServiceImp.mostrar();
	}
	
	// http://localhost:9000/gamer/genero/guardar
	@PostMapping (path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Genero genero){
		
		try {
			boolean response = generoServiceImp.guardar(genero);
			
			if(response == true)
				return new ResponseEntity<String>("Ya existe ese registro", HttpStatus.OK);
			else 
				return new ResponseEntity<Genero>(genero, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error al guardar" + e.getMessage(), HttpStatus.OK);
		}
	}
	
	// http://localhost:9000/gamer/genero/idBuscar
	@GetMapping (path = "{idGenero}")
	public ResponseEntity<?> buscar(@PathVariable("idGenero") Long idGenero){
		Genero response = generoServiceImp.buscar(idGenero);
		if(response == null)
			return new ResponseEntity<String>("No existe ese registro", HttpStatus.OK);
		else
			return new ResponseEntity<Genero>(response, HttpStatus.OK);
	}
	
	// http://localhost:9000/gamer/genero/editar
	@PutMapping (path = "editar")
	public ResponseEntity<?> editar(@RequestBody Genero genero){
		try {
			boolean response = generoServiceImp.editar(genero);
			
			if(response == false)
				return new ResponseEntity<String>("No existe ese registro", HttpStatus.OK);
			else
				return new ResponseEntity<Genero>(genero, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error al editar " + e.getMessage(), HttpStatus.OK);
		}
	}
	
	// http://localhost:9000/gamer/genero/idEiminar
	@DeleteMapping (path = "{idEliminar}")
	public ResponseEntity<?> eliminar(@PathVariable("idEliminar") Long idEliminar){
		try {
			boolean response = generoServiceImp.eliminar(idEliminar);
			
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
