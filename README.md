<table>
<tr>
<td width="100px"><img src="https://user-images.githubusercontent.com/103035621/170483040-a88d598b-145b-4903-accb-948ceff05811.png" alt="Team DOU"/></td>
<td width="1100px"> <h2>MSKA: Spring + JPA + MYSQL + Spring Security UD26_Ejercicio-2</h2> </td>

</tr>
</table>

[![Java](https://img.shields.io/badge/Java-FrontEnd-informational)]()
[![GitHub](https://img.shields.io/badge/GitHub-Repository-lightgrey)]()
[![SQL](https://img.shields.io/badge/SQL-DataBase-yellowgreen)]()
[![Spring](https://img.shields.io/badge/Spring-infrastructure-brightgreen)]()
[![Maven](https://img.shields.io/badge/Maven-ProjectStructure-blueviolet)]()

Este ejercicio ha sido realizado por los miembros del equipo 1. Dicho equipo esta formado por:

[- Ixabel Justo Etxeberria](https://github.com/Kay-Nicte)<br>
[- J.Oriol López Bosch](https://github.com/mednologic)<br>
[- Octavio Bernal](https://github.com/OctavioBernalGH)<br>
[- David Dalmau](https://github.com/DavidDalmauDieguez)

<p align="justify">Se crea un proyecto Maven utilizando la tecnología spring, se definen como componentes los spring services, la base de datos H2 y JPA. Se crea la estructura de proyecto en capas definiendo los paquetes de controllers, dao, dto y services. Para proseguir se crean las entidades 'cientifico', 'proyecto',  y 'asignado_a' con una relación de uno a muchos (one to many). Se definen las columnas y mediante anotaciones se mapea con los atributos de la entidad.</p>

A continuación se expondrá el desarrollo completo de la aplicación. A la hora de comenzar con un proyecto Spring es muy importante configurar el fichero application.propierties ubicado en la carpeta de resources. En este caso la configuración queda tal que así:

<p align="center">
  <img src="https://user-images.githubusercontent.com/71872946/170114592-755fa299-86eb-42b5-9356-cf089a8fd440.png">
</p>

El siguiente paso corresponde a la definición de la base de datos según el esquema entidad relación, para ello se ha creado un script de creación de base de datos con varias tuplas de datos de prueba. El fichero es denominado data.sql y está ubicado en la misma carpeta que el application.propierties. A continuación se puede observar el código generado:

```sql
USE UD26_Ejercicio_2;

CREATE table cientifico(
id int auto_increment,
dni nvarchar(255) not null,
nombre_apels nvarchar(255),
primary key (id));

CREATE TABLE proyecto(
id int auto_increment,
nombre nvarchar(255),
horas int,
primary key (id));

CREATE TABLE asignado_a(
id int auto_increment,
cientifico int,
proyecto int,
primary key (id),
foreign key (cientifico) references cientifico(id),
foreign key (proyecto) references proyecto(id));

insert into `cientifico` (`dni`, `nombre_apels`) values ('298384s', 'David');
insert into `cientifico` (`dni`, `nombre_apels`) values ('2984214a', 'Uri');
insert into `cientifico` (`dni`, `nombre_apels`) values ('2982141244d', 'Octavio');
insert into `cientifico` (`dni`, `nombre_apels`) values ('29421412l', 'Ixabel');

insert into `proyecto` (`nombre`, `horas`) values ('David', '12');
insert into `proyecto` (`nombre`, `horas`) values ('Uri', '13');
insert into `proyecto` (`nombre`, `horas`) values ('Octavio', '3');
insert into `proyecto` (`nombre`, `horas`) values ('Ixabel', '16');

select * from cientifico;
select * from proyecto;

insert into `asignado_a` (`cientifico`, `proyecto`) values ('1', '1');
insert into `asignado_a` (`cientifico`, `proyecto`) values ('2', '2');
insert into `asignado_a` (`cientifico`, `proyecto`) values ('3', '3');
insert into `asignado_a` (`cientifico`, `proyecto`) values ('4', '4');
```

El siguiente paso será crear las diferentes clases, cada clase representa una tabla y los atributos de cada clase representan las columnas de la base de datos, de esta manera mediante las diferentes anotaciones creamos un mapeo entre objeto-tabla.

Las principales anotaciones a la hora de mapear una clase con la tabla son:

@Entity --> Se va a crear una entidad.<br>
@Table(name = "nombre de la tabla") --> El nombre de la tabla corresponde a...<br>
@Id --> El siguiente atributo será la clave primaria.<br>
@GeneratedValue(strategy = GeneratedType.IDENTITY) --> La clave primaria se generará de forma auto incremental.<br>
@Column(name = "nombre_col") --> El nombre de la columna corresponde al atributo de abajo.<br>
private TipoVariable nombreCol;

A continuación se dejan tres spoilers con el código generado en las clases.

<details>
  
<summary>Código entidad Cientifico</summary>
  
<br>
  
```java
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
	private Long id;
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

	public Cientifico(Long id, String dni, String nombre_apels, List<AsignadoA> asignadoA) {

		this.id = id;
		this.nombre_apels = nombre_apels;
		this.asignadoA = asignadoA;
	}

	// getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
 
```
  
</details>
  
<details>
  
<summary>Código entidad Proyecto</summary>
  
<br>
  
```java
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
@Table(name = "proyecto")
public class Proyecto {

	// atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "horas")
	private int horas;
	@OneToMany
	@JoinColumn(name = "id")
	private List<AsignadoA> asignadoA;

	// Constructores
	public Proyecto() {

	}

	public Proyecto(Long id, String nombre, int horas, List<AsignadoA> asignadoA) {

		this.id = id;
		this.nombre = nombre;
		this.horas = horas;
		this.asignadoA = asignadoA;
	}

	// getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
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
		return "Proyecto [id=" + id + ", nombre=" + nombre + ", horas=" + horas + "]";

	}
}
```
  
</details>

<details>

<summary>Código entidad AsgignadoA</summary>
  
<br>
  
```java
package com.mska.ejercicio.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "asignado_a")
public class AsignadoA {
	
	// atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	@ManyToOne
	@JoinColumn(name = "cientifico")
	Cientifico cientifico;
	@ManyToOne
	@JoinColumn(name = "proyecto")
	Proyecto proyecto;

	// construstores
	public AsignadoA() {
		
	}

	public AsignadoA(Long id, Cientifico cientifico, Proyecto proyecto) {
		
		this.id = id;
		this.cientifico = cientifico;
		this.proyecto = proyecto;
	}

	// getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cientifico getCientifico() {
		return cientifico;
	}

	public void setCientifico(Cientifico cientifico) {
		this.cientifico = cientifico;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	// método toString
	@Override
	public String toString() {
		return "AsignadoA [id=" + id + ", cientifico=" + cientifico + ", proyecto=" + proyecto + "]";
	}
}
  
```
</details>
	
Una vez definidas las entidades del paquete DTO se procederá a la creación de las interfaces DAO, dichas interfaces heredan los métodos CRUD básicos del repositorio Jpa, para ello las tres interfaces extenderan JpaRepository. Jpa proporciona un modelo de datos genérico referente al CRUD. También se definira la anotación @Repository para indicar el acceso a la base de datos. En el siguiente cuadro de texto se muestra el código generado en los diferentes DAO , se ubica todo en este cuadro debido a la escaséz de código.
	
```java
package com.mska.ejercicio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mska.ejercicio.dto.AsignadoA;

public interface IAsignadosADAO extends JpaRepository<AsignadoA, Long> {

}
  
package com.mska.ejercicio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mska.ejercicio.dto.Cientifico;

public interface ICientificosDAO extends JpaRepository<Cientifico, Long>{

}
  
package com.mska.ejercicio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mska.ejercicio.dto.Proyecto;

public interface IProyectosDAO extends JpaRepository<Proyecto, Long>{

}
  
```

Se proseguirá creando las interfaces de los métodos que se utilizarán en la capa service, en este caso serán 3 interfaces, la de cientificos, proveproyectos y asignado_a. En dichas interfaces se generará la cabecera de los métodos que implementarán las diferentes clases de la capa service y que posteriormente se desarrollarán y mapearán en los controladores, a continuación se introduce el código generado para las interfaces en desplegables.
	
<details>
	
<summary>Interface Cienetificos</summary>
	
<br>
	
```java
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
  
```
	
</details>
	
	
<details>
	
<summary>Interface Proyectos</summary>
	
<br>
	
```java
  
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
  
```
	
</details>
	
	
<details>
	
<summary>Interfaz Asignado_a</summary>
	
<br>
	
```java
  
package com.mska.ejercicio.service;

import java.util.List;

import com.mska.ejercicio.dto.AsignadoA;

public interface IAsignadoAService {

	// Listar, Guardar, ListarXID, actualizar, eliminar
	public List<AsignadoA> listarAsignadosA();

	public AsignadoA guardarAsignadosA(AsignadoA asignadoA);

	public AsignadoA listarAsignadosAXID(Long id);

	public AsignadoA actualizarAsignadosA(AsignadoA asignadoA);

	public void eliminarAsignadosA(Long id);

}
  
```
	
</details>
  
Completadas ya las interfaces se procederá a la creación de las clases que las implementarán. Una vez credas se definirá la anotación @Service indicando que esta clase pertenece a la capa de servicios y la anotación @Autowired que inyectará las dependencias del Jpa heredadas del dao. Una vez definido el 'implements' en la cabecera de la clase, eclipse pedirá aplicar los métodos de las interfaces.
	
Se rellenará el cuerpo de los métodos con los recibidos por Jpa, Jpa tiene funciones própias de CRUD, a continuación hay tres desplegables con el código generado en las diferentes clases de la capa service.
	
<details>
	
<summary>Clase Service de Cientificos</summary>

<br>

```java

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
	public Cientifico listarCientificoXID(Long id) {
		return iCientificosDAO.findById(id).get();
	}

	@Override
	public Cientifico actualizarCientifico(Cientifico cientifico) {
		return iCientificosDAO.save(cientifico);
	}

	@Override
	public void eliminarCientifico(Long id) {
		iCientificosDAO.deleteById(id);
		
	}

}
  
```
	
</details>
	
	
<details>
	
<summary>Clase Service de Proyectos</summary>

<br>

```java
  
package com.mska.ejercicio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mska.ejercicio.dao.IProyectosDAO;
import com.mska.ejercicio.dto.Proyecto;

@Service
public class ProyectoServiceImp implements IProyectoService{
	
	@Autowired
	IProyectosDAO iProyectosDAO;

	@Override
	public List<Proyecto> listarProyecto() {
		return iProyectosDAO.findAll();
	}

	@Override
	public Proyecto guardarProyecto(Proyecto proyecto) {
		return iProyectosDAO.save(proyecto);
	}

	@Override
	public Proyecto listarProyectoXID(Long id) {
		return iProyectosDAO.findById(id).get();
	}

	@Override
	public Proyecto actualizarProyecto(Proyecto proyecto) {
		return iProyectosDAO.save(proyecto);
	}

	@Override
	public void eliminarProyecto(Long id) {
		iProyectosDAO.deleteById(id);
		
	}
}
  
```
	
</details>
	
	
<details>
	
<summary>Clase Service de Asignado_a</summary>

<br>

```java
  
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
  
```
	
</details>
  
Para finalizar la parte java faltará crear los controladores, para ello en la capa controllers se definen los controladores piezas, proveedores y suministra. En la clase controller se tendrán que añadir las anotaciones @RestController para indicarle a spring que este controlador es del tipo rest, @RequestMapping("/api") para la raíz de la aplicación referente a los endpoints, la anotación @Autowired para inyectar las dependencias de la capa service definidos e implementados sobre la clase 'CientificosServiceImpl', 'ProyectoServiceImpl' y 'AsignadoAServiceImpl'.
	
Para finalizar con la explicación de las anotaciones se utilizarán los mapeos de método HTTP, dichas anotaciones son:
	
@GetMapping("/ruta api") --> Método utilizado para obtener datos.<br>
@PostMapping("/ruta api") --> Método utilizado para crear algún tipo de registro.<br>
@PutMapping("/ruta api") --> Método para actualizar registros.<br>
@DeleteMapping("/ruta api") --> Método para eliminar un registro.<br>
	
A continuación se generan los desplegables que hacen referencia a los controladores creados:
	
<details>
	
<summary>Cientificos Controller</summary>
	
<br>
	
```java
package com.mska.ejercicio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mska.ejercicio.dto.Cientifico;
import com.mska.ejercicio.service.CientificoServiceImp;


@RestController
@RequestMapping("/api")
public class CientificoController {
	@Autowired
	CientificoServiceImp cientificoServiceImp;

	@GetMapping("/cientifico")
	public List<Cientifico> listarCientificos() {
		return cientificoServiceImp.listarCientificos();
	}

	@PostMapping("/cientifico")
	public Cientifico crearCientifico(@RequestBody Cientifico cientifico) {

		return cientificoServiceImp.guardarCientifico(cientifico);
	}

	@GetMapping("/cientifico/{id}") 
	public Cientifico listarCientificoXID(@PathVariable(name = "id") Long id) {

		Cientifico cientificoPorId = new Cientifico();
		cientificoPorId = cientificoServiceImp.listarCientificoXID(id);

		return cientificoPorId;
	}

	@PutMapping("/cientifico/{id}") 
	public Cientifico actualizarCientifico(@PathVariable(name = "id") Long id, @RequestBody Cientifico cientifico) {
		Cientifico cientificoGetted = new Cientifico();
		Cientifico cientificoAct = new Cientifico();

		cientificoGetted = cientificoServiceImp.listarCientificoXID(id);

		cientificoGetted.setId(cientifico.getId());
		cientificoGetted.setDni(cientifico.getDni());
		cientificoGetted.setNombre_apels(cientifico.getNombre_apels());

		cientificoAct = cientificoServiceImp.actualizarCientifico(cientificoGetted);

		return cientificoAct;
	}

	@DeleteMapping("/cientifico/{id}") 
	public void eliminarCientifico(@PathVariable(name = "id") Long id) {
		cientificoServiceImp.eliminarCientifico(id);
	}

}
  
```
	
</details>
	
	
<details>
	
<summary>Proyecto Controller</summary>
	
<br>
	
```java
  
package com.mska.ejercicio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mska.ejercicio.dto.Proyecto;
import com.mska.ejercicio.service.ProyectoServiceImp;

@RestController
@RequestMapping("/api")
public class ProyectoController {
	@Autowired
	ProyectoServiceImp proyectoServiceImp;

	//Listar, Guardar, ListarXID, actualizar, eliminar
	@GetMapping("/proyecto")
	public List <Proyecto> listarProyectos(){
		return proyectoServiceImp.listarProyecto();
	};
	
	@PostMapping("/proyecto")
	public Proyecto guardarProyecto(@RequestBody Proyecto proyecto) {
		
		return proyectoServiceImp.guardarProyecto(proyecto);
		
	}
	
	@GetMapping("/proyecto/{id}")
	public Proyecto listarProyectosXID(@PathVariable(name = "id") Long id){	
		return proyectoServiceImp.listarProyectoXID(id);
	}
	
	@PutMapping("/proyecto/{id}")
	public Proyecto actualizarProyecto(@PathVariable(name = "id") Long id, @RequestBody Proyecto proyecto) {
		Proyecto proyectoGetted = new Proyecto();
		Proyecto proyectoAct 	= new Proyecto();
		
		
		proyectoGetted = proyectoServiceImp.listarProyectoXID(id);

		proyectoGetted.setId(proyecto.getId());
		proyectoGetted.setNombre(proyecto.getNombre());
		proyectoGetted.setHoras(proyecto.getHoras());

		proyectoAct = proyectoServiceImp.actualizarProyecto(proyectoGetted);
		
		return proyectoAct;
		
	}
	
	@DeleteMapping("/proyecto/{id}")
	public void eliminarProyecto(@PathVariable(name = "id") Long id) {
		proyectoServiceImp.eliminarProyecto(id);
	}

}
  
```
	
</details>
	
	
<details>
	
<summary>AsignadoA Controller</summary>
	
<br>
	
```java
  
package com.mska.ejercicio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mska.ejercicio.dto.AsignadoA;
import com.mska.ejercicio.service.AsignadoAServiceImp;

@RestController
@RequestMapping("/api")
public class AsignadoAController {
	
	@Autowired
	AsignadoAServiceImp asignadoAServiceImpl;

	@GetMapping("/asignado_a")
	public List<AsignadoA> listarAsignadoAs() {
		return asignadoAServiceImpl.listarAsignadosA();
	}

	@PostMapping("/asignado_a")
	public AsignadoA guardarAsignadoA(@RequestBody AsignadoA asignadoA) {

		return asignadoAServiceImpl.guardarAsignadosA(asignadoA);
	}

	@GetMapping("/asignado_a/{id}")
	public AsignadoA listarAsignadoAXID(@PathVariable(name = "id") Long id) {

		AsignadoA asignadoAXID = new AsignadoA();
		asignadoAXID = asignadoAServiceImpl.listarAsignadosAXID(id);

		return asignadoAXID;
	}

	@PutMapping("/asignado_a/{id}")
	public AsignadoA actualizarAsignadoA(@PathVariable(name = "id") Long id, @RequestBody AsignadoA asignadoA) {
		AsignadoA asignadoASeleccionado = new AsignadoA();
		AsignadoA asignadoAActualizado = new AsignadoA();

		asignadoASeleccionado = asignadoAServiceImpl.listarAsignadosAXID(id);

		asignadoASeleccionado.setCientifico(asignadoA.getCientifico());
		asignadoASeleccionado.setProyecto(asignadoA.getProyecto());

		asignadoAActualizado = asignadoAServiceImpl.actualizarAsignadosA(asignadoAActualizado);

		return asignadoAActualizado;
	}

	@DeleteMapping("/asignado_a/{id}")
	public void eleiminarAsignadoA(@PathVariable(name = "id") Long id) {
		asignadoAServiceImpl.eliminarAsignadosA(id);
	}
}
  
```
	
</details>

A estas alturas lo único que quedará será verificar el funcionamiento del aplicativo, para ello se utilizará postman para testear los endpoints de cada entidad. Lo primero de todo será proceder con la comprobación de obtener todos los datos de cientificos, proyectos y asignado_a, para ello se utilizará el método HTTP GET apuntando a la dirección del controlador referente a listar. En las siguientes tres imagenes se muestra el resultado:

<p align="center">
	<img src="https://user-images.githubusercontent.com/71872946/170120647-d568cb43-531d-4342-b313-fd1b64a59b00.png">
</p>
  
<p align="center">
	<img src="https://user-images.githubusercontent.com/71872946/170120602-e5c5d198-0253-42af-8cf1-c5dc05ec9682.png">
</p>
  
<p align="center">
	<img src="https://user-images.githubusercontent.com/71872946/170120553-9a14e85a-dc01-446c-af64-7a599667076a.png">
</p>

La siguiente verificación pasará por el endpoint de buscar un componente por identificador, para ello se utilizará el método HTTP GET apuntando a la dirección del controlador referente a listar. En las siguientes tres imagenes se muestra el resultado:
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/71872946/170121016-f3643ee1-f413-464a-bea6-70e1c7178c50.png">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/71872946/170121039-2c403f09-9036-485b-8e86-ab4cd4a7a9b1.png">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/71872946/170121064-7e58b9ac-ac61-4cdc-948d-18612597a6bd.png">
</p>
  
El siguiente procedimiento de verificación constará de apuntar al endpoint de modificación de datos de entidad para ello se utilizará el método HTTP PUT indicando en la URI del endpoint el identificador del componentes y introduciendo en el body los datos a modificar en JSON. Se muestra el ejemplo en las siguientes capturas de pantalla:
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/71872946/170121297-2a708f1f-1aca-42cb-938f-14d55ea26615.png">
</p>

<p align="center">
	<img src="https://user-images.githubusercontent.com/71872946/170121325-afecbcaf-26e5-4442-8f2c-e0019d709001.png">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/71872946/170121359-13b69712-9aaa-490e-8d23-53d8d88f07a8.png">
</p>
  
A continuación se verificará la eliminación de registros de las diferentes tablas, para ello se utilizará el método HTTP DELETE apuntando al endpoint correspondiente, en este caso también se pedirá al usuario que introduzca el identificador del componente a eliminar, al ser de tipo void no mostrará nada por pantalla, para mostrar correctamente la eliminación se muestra en consola un mensaje. Se puede visualizar el ejemplo en las diferentes capturas:
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/71872946/170121626-24974475-dc9d-4245-b324-13f26ca858a0.png">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/71872946/170121655-cae90f4c-a914-4788-aac6-7b211a03e2e2.png">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/71872946/170121672-0b142579-63a6-4e49-a48b-ae75c5fd3697.png">
</p>

Por último verificar la creación de nuevos elementos en las diferentes tablas, para ello se utiliza el método HTTP POST seguido de la ruta del endpoint, se ha de especificar en el body los datos del nuevo componente a crear. En las siguientes capturas se observa un ejemplo:
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/71872946/170121855-e56d8e2b-6ad7-4ef7-9233-2e4c5e6c2571.png">
</p>

<p align="center">
	<img src="¡https://user-images.githubusercontent.com/71872946/170121883-03ac23fe-ba97-4a99-a765-ac70201e6b4f.png">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/71872946/170121899-67e85216-fca3-4359-bcd4-cf17a1ee3661.png">
</p>
  
La actividad se da por finalizada, como observación es muy importante la nomenclatura utilizada a la hora de guardar las capturas de pantalla para la posterior documentación, ya que esto facilitará muchísimo el trabajo futuro.
  
También es muy importante verificar el funcionamiento mediante Postman, porque un fallo en un atributo, un método o en el propio mapeo puede provocar un fallo en cadena.
