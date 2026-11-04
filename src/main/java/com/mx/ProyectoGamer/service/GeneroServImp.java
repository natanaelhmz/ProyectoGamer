package com.mx.ProyectoGamer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ProyectoGamer.entity.Genero;
import com.mx.ProyectoGamer.repository.GeneroRepository;

@Service
public class GeneroServImp {

	@Autowired
	GeneroRepository generoRepository;
	
	@Transactional(readOnly = true)
	public List<Genero> mostrar() {
		return generoRepository.findAll();
	}
	
	@Transactional
	public boolean guardar(Genero genero) {
		
		for(Genero g: mostrar()) {
			if(g.getNombre().equals(genero.getNombre())) {
				return true;
			}
		}
		
		generoRepository.save(genero);
		return false;
	}
	
	@Transactional(readOnly = true)
	public Genero buscar(Long idGenero) {
		return generoRepository.findById(idGenero).orElse(null);
	}
	
	@Transactional
	public boolean editar(Genero genero) {
		Genero generoEnco = buscar(genero.getIdCategoria());
		
		if(generoEnco == null)
			return false;
		else {
			generoRepository.save(genero);
			return true;
		}
	}
	
	@Transactional
	public boolean eliminar(Long idEliminar) {
		Genero generoEnco = buscar(idEliminar);
		
		if(generoEnco == null)
			return false;
		else {
			generoRepository.deleteById(idEliminar);
			return true;
		}
	}
}
