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

import com.mska.ejercicio.dto.AsignadoA;
import com.mska.ejercicio.service.AsignadoAServiceImp;

@RestController
@RequestMapping("/api")
public class AsignadoAController {
	
	@Autowired
	AsignadoAServiceImp asignadoAServiceImpl;

	@GetMapping("/asignado")
	public List<AsignadoA> listarAsignadoAs() {
		return asignadoAServiceImpl.listarAsignadosA();
	}

	@PostMapping("/asignado")
	public AsignadoA guardarAsignadoA(@RequestBody AsignadoA asignadoA) {

		return asignadoAServiceImpl.guardarAsignadosA(asignadoA);
	}

	@GetMapping("/asignado/{id}")
	public AsignadoA listarAsignadoAXID(@PathVariable(name = "id") String id) {

		AsignadoA asignadoAXID = new AsignadoA();
		asignadoAXID = asignadoAServiceImpl.listarAsignadosAXID(id);

		return asignadoAXID;
	}

	@PutMapping("/asignado/{id}")
	public AsignadoA actualizarAsignadoA(@PathVariable(name = "id") String id, @RequestBody AsignadoA asignadoA) {
		AsignadoA asignadoASeleccionado = new AsignadoA();
		AsignadoA asignadoAActualizado = new AsignadoA();

		asignadoASeleccionado = asignadoAServiceImpl.listarAsignadosAXID(id);

		asignadoASeleccionado.setCientifico(asignadoA.getCientifico());
		asignadoASeleccionado.setProyecto(asignadoA.getProyecto());

		asignadoAActualizado = asignadoAServiceImpl.actualizarAsignadosA(asignadoAActualizado);

		return asignadoAActualizado;
	}

	@DeleteMapping("/asignado/{id}")
	public void eleiminarAsignadoA(@PathVariable(name = "id") String id) {
		asignadoAServiceImpl.eliminarAsignadosA(id);
	}
}
