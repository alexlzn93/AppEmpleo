package com.alexlzn.interfaces;

import java.util.List;

import com.alexlzn.model.Perfil;

public interface IPerfilService {

	public void guardar(Perfil perfil);
	public void eliminar( Integer idperfil);
	public List<Perfil> findAllPerfiles();
	public Perfil buscarPorId(Integer idperfil);
	public void crearPerfiles();
}
