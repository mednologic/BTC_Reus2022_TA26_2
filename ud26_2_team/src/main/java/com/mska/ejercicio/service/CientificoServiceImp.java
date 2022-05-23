package com.mska.ejercicio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mska.ejercicio.dao.ICientificosDAO;
import com.mska.ejercicio.dto.Cientifico;

@Service
public class CientificoServiceImp implements ICientificoService{
	
	@Autowired
	ICientificosDAO iCientificosDAO;

	@Override
	public List<Cientifico> listarCientificos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cientifico guardarCientifico(Cientifico cientifico) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cientifico listarCientificoXID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cientifico actualizarCientifico(Cientifico cientifico) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarCientifico(String id) {
		// TODO Auto-generated method stub
		
	}

}
