package com.alexlzn.interfaces;

import java.util.List;

import com.alexlzn.model.Usuario;

public interface IUsuarioService {

	public void guardar(Usuario usuario);
	public void delete(Integer idusuario);
	public List<Usuario> findAllUsuarios();
	public Usuario buscarPorId(Integer idusuario);
	public Usuario findByUsername(String username);
	public Usuario bloquearUsuario(Integer idUser);
	public Usuario desbloquearUsuario(Integer idUser);
}
