package com.alexlzn.interfaces;

import java.util.List;

import com.alexlzn.model.Perfil;

public interface IPerfilService {

	public void guardar(Perfil perfil);
	public void eliminar( int idperfil);
	public List<Perfil> findAllPerfiles();
	public Perfil buscarPorId(int idperfil);
	public void crearPerfiles();
}
