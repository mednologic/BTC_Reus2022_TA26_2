package com.mska.ejercicio.service;

import java.util.List;

import com.mska.ejercicio.dto.AsignadoA;


public interface IAsignadoAService {
	
	//Listar, Guardar, ListarXID, actualizar, eliminar
		public List<AsignadoA> listarAsignadosA();
		public AsignadoA guardarAsignadosA(AsignadoA asignadoA);
		public AsignadoA listarAsignadosAXID(String id);
		public AsignadoA actualizarAsignadosA(AsignadoA asignadoA);
		public void eliminarAsignadosA(String id);

}