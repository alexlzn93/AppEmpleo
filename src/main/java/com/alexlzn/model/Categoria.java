package com.alexlzn.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Categoria {
	@Id
	private String nombre;
	private String descripcion;
	
	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Categoria(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
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
		return "Categoria [nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
}
