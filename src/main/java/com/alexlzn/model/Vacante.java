package com.alexlzn.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="vacantes")
public class Vacante implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String nombre;
	@Lob
	private String descripcion;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private double salario;
	private int destacado; // 1=destacada 0=no destacada
	private String images = "no_image.jpg";
	private String status;
	@Lob
	private String detalles;
	@ManyToOne
	@JoinColumn(name="idcategoria")
	private Categoria categoria;
	@OneToMany(mappedBy="vacante")
	private List<Solicitud> solicitudes;

	public Vacante() {
		super();
		// TODO Auto-generated constructor stub
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

	public int getDestacado() {
		return destacado;
	}

	public void setDestacado(int destacado) {
		this.destacado = destacado;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	

	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

	@Override
	public String toString() {
		return "Vacante [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha
				+ ", salario=" + salario + ", destacado=" + destacado + ", images=" + images + ", status=" + status
				+ ", detalles=" + detalles + ", categoria=" + categoria + ", solicitudes=" + solicitudes + "]";
	}

	

}
