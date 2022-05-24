package com.mska.ejercicio.dto;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cientifico")
public class Cientifico {

	// atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "id")
	private String id;
	@Column(name="dni")
	private String dni;
	@Column(name = "nombre_apels")
	private String nombre_apels;
	@OneToMany
	@JoinColumn(name = "id")
	private List<AsignadoA> asignadoA;

	// constructores
	public Cientifico() {

	}

	public Cientifico(String id, String dni, String nombre_apels, List<AsignadoA> asignadoA) {

		this.id = id;
		this.nombre_apels = nombre_apels;
		this.asignadoA = asignadoA;
	}

	// getters y setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre_apels() {
		return nombre_apels;
	}

	public void setNombre_apels(String nombre_apels) {
		this.nombre_apels = nombre_apels;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "AsignadoA")
	public List<AsignadoA> getAsignadoA() {
		return asignadoA;
	}

	public void setAsignadoA(List<AsignadoA> asignadoA) {
		this.asignadoA = asignadoA;
	}

	@Override
	public String toString() {
		return "Cientifico [id=" + id + ", dni=" + dni + ", nombre_apels=" + nombre_apels + ", asignadoA=" + asignadoA
				+ "]";
	}
}