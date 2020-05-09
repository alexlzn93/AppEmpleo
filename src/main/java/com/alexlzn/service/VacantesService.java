package com.alexlzn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexlzn.interfaces.IVacantesService;
import com.alexlzn.model.Vacante;
import com.alexlzn.repository.IVacanteRepository;

@Service
public class VacantesService implements IVacantesService {
	
	@Autowired //inyeccion del repository 
	IVacanteRepository vacanteRepo;
	
	@Override
	public List<Vacante> findAllVacantes() {
		System.out.println("Lista de todas las vacantes");
		List<Vacante> buscarTodas= (List<Vacante>) vacanteRepo.findAll();
		return buscarTodas;
	}

	@Override
	public void guardar(Vacante vacante) {
		System.out.println("Vacante guardada " + vacante);
		vacanteRepo.save(vacante);
	}

	@Override
	public void delete(int idVacante) {
		System.out.println("Vacante eliminada");
		vacanteRepo.deleteById(idVacante);
	}

	@Override
	public Vacante buscarPorId(int idVacante) {
		Optional<Vacante> vacante= vacanteRepo.findById(idVacante);
		if(vacante.isPresent()) {
			return vacante.get();
		}
		System.out.println("No se encuentra la vacante con id: " + idVacante);
		return null;
	}

	@Override
	public List<Vacante> vacantesDestacadas() {
		return vacanteRepo.findByDestacadoAndStatus(1, "Aprobada");
	}

	

}
