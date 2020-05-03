package com.alexlzn.interfaces;

import java.util.List;

import com.alexlzn.model.Usuario;

public interface IUsuarioService {

	public void guardar(Usuario usuario);
	public void delete(int idusuario);
	public List<Usuario> findAllUsuarios();
	public Usuario buscarPorId(int idusuario);
}
