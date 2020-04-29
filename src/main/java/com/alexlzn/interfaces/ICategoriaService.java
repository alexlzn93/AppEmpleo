package com.alexlzn.interfaces;

import java.util.List;

import com.alexlzn.model.Categoria;

public interface ICategoriaService {

	public void guardar(Categoria categoria);
	public void delete(int idcategoria); 
	public List<Categoria> findAllCategoria(); //buscar todas Categoria
	public Categoria buscarPorId(int idcategoria);
}
