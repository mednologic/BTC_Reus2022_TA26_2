package com.mska.ejercicio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mska.ejercicio.dto.Proyecto;

public interface IProyectosDAO extends JpaRepository<Proyecto, String>{

}
