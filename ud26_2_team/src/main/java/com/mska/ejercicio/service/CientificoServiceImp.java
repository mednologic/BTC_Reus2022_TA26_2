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
		return iCientificosDAO.findAll();
	}

	@Override
	public Cientifico guardarCientifico(Cientifico cientifico) {
		return iCientificosDAO.save(cientifico);
	}

	@Override
	public Cientifico listarCientificoXID(String id) {
		return iCientificosDAO.findById(id).get();
	}

	@Override
	public Cientifico actualizarCientifico(Cientifico cientifico) {
		return iCientificosDAO.save(cientifico);
	}

	@Override
	public void eliminarCientifico(String id) {
		iCientificosDAO.deleteById(id);
		
	}

}
