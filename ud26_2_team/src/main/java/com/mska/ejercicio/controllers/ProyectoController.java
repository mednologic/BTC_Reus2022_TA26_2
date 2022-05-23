package com.mska.ejercicio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mska.ejercicio.dto.Proyecto;
import com.mska.ejercicio.service.ProyectoServiceImp;


public class ProyectoController {
	@Autowired
	ProyectoServiceImp proyectoServiceImp;

	//Listar, Guardar, ListarXID, actualizar, eliminar
	@GetMapping("/proyectos")
	public List <Proyecto> listarProyectos(){
		return proyectoServiceImp.listarProyecto();
	};
	
	@PostMapping("/proyectos")
	public Proyecto guardarProyecto(@RequestBody Proyecto proyecto) {
		
		return proyectoServiceImp.guardarProyecto(proyecto);
		
	}
	
	@GetMapping("/proyectos/{id}")
	public List<Proyecto> listarProyectosXID(@PathVariable(name = "id") String id){
		
		Proyecto proyecto = new Proyecto();
		proyectoServiceImp.listarProyectoXID(id);
		return null;
	}
	
	@PutMapping("/proyectos/{id}")
	public Proyecto actualizarProyecto(@PathVariable(name = "id") String id, @RequestBody Proyecto proyecto) {
		Proyecto proyectoGetted = new Proyecto();
		Proyecto proyectoAct 	= new Proyecto();
		
		
		proyectoGetted = proyectoServiceImp.listarProyectoXID(id);

		proyectoGetted.setId(proyecto.getId());
		proyectoGetted.setNombre(proyecto.getNombre());
		proyectoGetted.setHoras(proyecto.getHoras());

		proyectoAct = proyectoServiceImp.actualizarProyecto(proyectoGetted);
		
		return proyectoAct;
		
	}
	@DeleteMapping("/proyectos/{id}")
	public void eliminarProyecto(@PathVariable(name = "id") String id) {
		proyectoServiceImp.eliminarProyecto(id);
	}

}
