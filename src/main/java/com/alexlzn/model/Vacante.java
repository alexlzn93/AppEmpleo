package com.alexlzn.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Vacante {
	@Id
	private int id;
	private String nombre;
	private String descripcion;
	private Date fecha;
	private double salario;
	private int destacada; //1=destacada 0=no destacada
	private String images="no_image.jpg";

	public Vacante() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Vacante(int id, String nombre, String descripcion, Date fecha, double salario, int destacada,
			String images) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.salario = salario;
		this.destacada = destacada;
		this.images = images;
	}




	public String getImages() {
		return images;
	}


	public void setImages(String images) {
		this.images = images;
	}


	public int getDestacada() {
		return destacada;
	}

	public void setDestacada(int destacada) {
		this.destacada = destacada;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}


	@Override
	public String toString() {
		return "Vacante [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha
				+ ", salario=" + salario + ", destacada=" + destacada + ", images=" + images + "]";
	}

	

}
