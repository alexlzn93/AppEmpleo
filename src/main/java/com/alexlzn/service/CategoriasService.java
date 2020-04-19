package com.alexlzn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexlzn.interfaces.ICategoriaService;
import com.alexlzn.model.Categoria;
import com.alexlzn.repository.ICategoriaRepository;
@Service
public class CategoriasService implements ICategoriaService {

	@Autowired
	ICategoriaRepository categoriaRepo;
	
	@Override
	public void guardar(Categoria categoria) {
		System.out.println("Guardando categoria");
		categoriaRepo.save(categoria);
	}

	@Override
	public void delete(Categoria categoria) {
		System.out.println("Eliminando categoria");
		categoriaRepo.delete(categoria);

	}

	@Override
	public List<Categoria> findAllCategoria() {
		System.out.println("Listado de Categorias");
		return (List<Categoria>) categoriaRepo.findAll();
	}

}
