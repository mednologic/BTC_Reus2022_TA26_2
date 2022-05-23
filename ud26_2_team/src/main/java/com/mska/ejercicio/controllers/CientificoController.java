package com.mska.ejercicio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mska.ejercicio.dto.Cientifico;
import com.mska.ejercicio.service.CientificoServiceImp;


@RestController
@RequestMapping("/api")
public class CientificoController {
	@Autowired
	CientificoServiceImp cientificoServiceImp;

	@GetMapping("/cientificos")
	public List<Cientifico> listarCientificos() {
		return cientificoServiceImp.listarCientificos();
	}

	@PostMapping("/cientificos")
	public Cientifico crearCientifico(@RequestBody Cientifico cientifico) {

		return cientificoServiceImp.guardarCientifico(cientifico);
	}

	@GetMapping("/cientificos/{id}") 
	public Cientifico listarCientificoXID(@PathVariable(name = "id") String id) {

		Cientifico cientificoPorId = new Cientifico();
		cientificoPorId = cientificoServiceImp.listarCientificoXID(id);

		return cientificoPorId;
	}

	@PutMapping("/cientificos/{id}") 
	public Cientifico actualizarCientifico(@PathVariable(name = "id") String id, @RequestBody Cientifico cientifico) {
		Cientifico cientificoGetted = new Cientifico();
		Cientifico cientificoAct = new Cientifico();

		cientificoGetted = cientificoServiceImp.listarCientificoXID(id);

		cientificoGetted.setId(cientifico.getId());
		cientificoGetted.setNombre(cientifico.getNombre());

		cientificoAct = cientificoServiceImp.actualizarCientifico(cientificoGetted);

		return cientificoAct;
	}

	@DeleteMapping("/cientificos/{id}") 
	public void eliminarCientifico(@PathVariable(name = "id") String dni) {
		cientificoServiceImp.eliminarCientifico(dni);
	}

}
