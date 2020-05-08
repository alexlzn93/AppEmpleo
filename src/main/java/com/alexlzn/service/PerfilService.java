package com.alexlzn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexlzn.interfaces.IPerfilService;
import com.alexlzn.model.Perfil;
import com.alexlzn.repository.IPerfilRepository;
import com.alexlzn.util.ListaPerfiles;
@Service
public class PerfilService implements IPerfilService {

	@Autowired
	IPerfilRepository perfilrepository;
	
	@Override
	public void guardar(Perfil perfil) {
		System.out.println("Guardando perfil" + perfil.getPerfil());
		perfilrepository.save(perfil);

	}
	@Override
	public void crearPerfiles() {
		ListaPerfiles perfiles = new ListaPerfiles();
		perfilrepository.saveAll(perfiles.listaPerfil());
		
	}

	@Override
	public void eliminar(int idperfil) {
		System.out.println("Eliminando perfil " + idperfil);
		perfilrepository.deleteById(idperfil);

	}

	@Override
	public List<Perfil> findAllPerfiles() {
		System.out.println("Lista de perfiles");
		return (List<Perfil>) perfilrepository.findAll();
	}

	@Override
	public Perfil buscarPorId(int idperfil) {
		Optional<Perfil> perfil=perfilrepository.findById(idperfil);
		if(perfil.isPresent()) {
			return perfil.get();
		}
		System.out.println("No se encuentra el perfil con id: " + idperfil);
		return null;
	}

	

}
