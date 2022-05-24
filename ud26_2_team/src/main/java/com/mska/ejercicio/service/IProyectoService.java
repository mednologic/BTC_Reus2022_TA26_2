package com.mska.ejercicio.service;

import java.util.List;

import com.mska.ejercicio.dto.Proyecto;

public interface IProyectoService {
	// Listar, Guardar, ListarXID, actualizar, eliminar
	public List<Proyecto> listarProyecto();

	public Proyecto guardarProyecto(Proyecto proyecto);

	public Proyecto listarProyectoXID(Long id);

	public Proyecto actualizarProyecto(Proyecto proyecto);

	public void eliminarProyecto(Long id);

}