package com.mska.ejercicio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mska.ejercicio.dto.Cientifico;

public interface ICientificosDAO extends JpaRepository<Cientifico, String>{

}
