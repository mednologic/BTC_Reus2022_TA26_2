package com.mska.ejercicio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mska.ejercicio.dao.IProyectosDAO;
import com.mska.ejercicio.dto.Proyecto;

@Service
public class ProyectoServiceImp implements IProyectoService{
	
	@Autowired
	IProyectosDAO iProyectosDAO;

	@Override
	public List<Proyecto> listarProyecto() {
		return iProyectosDAO.findAll();
	}

	@Override
	public Proyecto guardarProyecto(Proyecto proyecto) {
		return iProyectosDAO.save(proyecto);
	}

	@Override
	public Proyecto listarProyectoXID(Long id) {
		return iProyectosDAO.findById(id).get();
	}

	@Override
	public Proyecto actualizarProyecto(Proyecto proyecto) {
		return iProyectosDAO.save(proyecto);
	}

	@Override
	public void eliminarProyecto(Long id) {
		iProyectosDAO.deleteById(id);
		
	}
}
