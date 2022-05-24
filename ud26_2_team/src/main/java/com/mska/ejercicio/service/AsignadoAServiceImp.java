package com.mska.ejercicio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mska.ejercicio.dao.IAsignadosADAO;
import com.mska.ejercicio.dto.AsignadoA;

@Service
public class AsignadoAServiceImp implements IAsignadoAService {
	
	@Autowired
	IAsignadosADAO iAsignadosADAO;

	@Override
	public List<AsignadoA> listarAsignadosA() {
		return iAsignadosADAO.findAll();
	}

	@Override
	public AsignadoA guardarAsignadosA(AsignadoA asignadoA) {
		return iAsignadosADAO.save(asignadoA);
	}

	@Override
	public AsignadoA listarAsignadosAXID(Long id) {
		return iAsignadosADAO.findById(id).get();
	}

	@Override
	public AsignadoA actualizarAsignadosA(AsignadoA asignadoA) {
		return iAsignadosADAO.save(asignadoA);
	}

	@Override
	public void eliminarAsignadosA(Long id) {
		iAsignadosADAO.deleteById(id);
		
	}

	
	
}

