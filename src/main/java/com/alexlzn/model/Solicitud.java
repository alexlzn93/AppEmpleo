package com.alexlzn.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "solicitudes")

public class Solicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private Integer id;

	private String archivo;

	@Lob
	private String comentarios;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@OneToOne
	@JoinColumn(name = "id_usuario") // FK en la tabla de usuarios
	private Usuario usuario;

	@OneToOne
	@JoinColumn(name = "id_vacante") // FK en la tabla de solicitudes
	private Vacante vacante;

	public Solicitud() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Vacante getVacante() {
		return vacante;
	}

	public void setVacante(Vacante vacante) {
		this.vacante = vacante;
	}

}