package com.alexlzn.interfaces;

import java.util.List;

import com.alexlzn.model.Vacante;

public interface IVacantesService {

	public List<Vacante> findAllVacantes();
	public void guardar (Vacante vacante);
	public void delete (int idVacante);
	public Vacante buscarPorId( int idVacante);
	
	public Vacante findPorId( int idVacante); //buscara en ListaVacantes (NO BASE DE DATOS)
	public List<Vacante> listVacantes(); //este metodo es sin JPA (NO BASE DE DATOS)
}
