package com.mska.ejercicio.service;

import java.util.List;

import com.mska.ejercicio.dto.Cientifico;

public interface ICientificoService {
	// Listar, Guardar, ListarXID, actualizar, eliminar
	public List<Cientifico> listarCientificos();

	public Cientifico guardarCientifico(Cientifico cientifico);

	public Cientifico listarCientificoXID(Long id);

	public Cientifico actualizarCientifico(Cientifico cientifico);

	public void eliminarCientifico(Long id);

}
