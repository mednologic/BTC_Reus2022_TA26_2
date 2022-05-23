package com.mska.ejercicio.dto;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cientificos")
public class Cientifico {

	// atributos
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "nombre")
	private String nombre;
	@OneToMany
	@JoinColumn(name = "id")
	private List<AsignadoA> asignadoA;

	// constructores
	public Cientifico() {

	}

	public Cientifico(String id, String nombre, List<AsignadoA> asignadoA) {

		this.id = id;
		this.nombre = nombre;
		this.asignadoA = asignadoA;
	}

	// getters y setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "AsignadoA")
	public List<AsignadoA> getAsignadoA() {
		return asignadoA;
	}

	public void setAsignadoA(List<AsignadoA> asignadoA) {
		this.asignadoA = asignadoA;
	}

	// método toString
	@Override
	public String toString() {
		return "Cientifico [dni=" + id + ", nombre=" + nombre + "]";
	}

}