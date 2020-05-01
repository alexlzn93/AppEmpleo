package com.alexlzn.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcategoria;
	private String nombre;
	private String descripcion;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="idcategoria",insertable=false,updatable=false)
	private List<Vacante> vacantes;
	
	
	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public List<Vacante> getVacantes() {
		return vacantes;
	}


	public void setVacantes(List<Vacante> vacantes) {
		this.vacantes = vacantes;
	}


	public int getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Categoria [idcategoria=" + idcategoria + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

	

}
