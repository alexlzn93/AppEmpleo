package com.alexlzn.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//BEANS PERSISTENCIA
@Entity
@Table(name = "categorias")
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcategoria;
	private String nombre;
	private String descripcion;

	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(Integer idcategoria) {
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
