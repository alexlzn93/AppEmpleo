package com.alexlzn.util;

import java.util.ArrayList;
import java.util.List;

import com.alexlzn.model.Perfil;

public class ListaPerfiles {

	public List<Perfil> listaPerfil(){
		List<Perfil> lista= new ArrayList();
		Perfil perfil1= new Perfil();
		perfil1.setPerfil("ADMINISTRADOR");
		Perfil perfil2= new Perfil();
		perfil2.setPerfil("SUPERVISOR");
		Perfil perfil3= new Perfil();
		perfil3.setPerfil("USUARIO");
		
		lista.add(perfil1);
		lista.add(perfil2);
		lista.add(perfil3);
		return lista;
	}
}
