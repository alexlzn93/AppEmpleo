package com.alexlzn.service;

import java.util.List;
import java.util.Optional;

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
	public void delete(Integer idcategoria) {
		System.out.println("Eliminando categoria");
		categoriaRepo.deleteById(idcategoria);

	}

	@Override
	public List<Categoria> findAllCategoria() {
		System.out.println("Listado de Categorias");
		return (List<Categoria>) categoriaRepo.findAll();
		
	}

	@Override
	public Categoria buscarPorId(Integer idcategoria) {
		Optional<Categoria> categoria=categoriaRepo.findById(idcategoria);
		if(categoria.isPresent()) {
			return categoria.get();
		}
		System.out.println("No se encuentra la categoria con ID: " + idcategoria);
		return null;
	}

	

}
