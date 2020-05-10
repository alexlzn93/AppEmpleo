package com.alexlzn.interfaces;

import java.util.List;

import org.springframework.data.domain.Example;

import com.alexlzn.model.Vacante;

public interface IVacantesService {

	public List<Vacante> findAllVacantes();
	public void guardar (Vacante vacante);
	public void delete (int idVacante);
	public Vacante buscarPorId( int idVacante);
	public List<Vacante> vacantesDestacadas();
	//CONSULTA QUERYBYEXAMPLE - WHERE DINAMICA DEPENDIENDO DEL BEANS
	public List<Vacante> findByExample(Example<Vacante> example);
}
