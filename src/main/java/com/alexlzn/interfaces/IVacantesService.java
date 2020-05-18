package com.alexlzn.interfaces;

import java.util.List;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alexlzn.model.Vacante;

public interface IVacantesService {

	public List<Vacante> findAllVacantes();
	public void guardar (Vacante vacante);
	public void delete (Integer idVacante);
	public Vacante buscarPorId( Integer idVacante);
	public List<Vacante> vacantesDestacadas();
	//CONSULTA QUERYBYEXAMPLE - WHERE DINAMICA DEPENDIENDO DEL BEANS
	public List<Vacante> findByExample(Example<Vacante> example);
	//PAGINAR VACANTES EN INDEX
	public Page<Vacante> findAllVacantesPaginacion(Pageable page);
}
