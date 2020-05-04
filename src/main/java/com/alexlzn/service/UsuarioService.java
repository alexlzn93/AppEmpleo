package com.alexlzn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexlzn.interfaces.IUsuarioService;
import com.alexlzn.model.Usuario;
import com.alexlzn.repository.IUsuarioRepository;
@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	IUsuarioRepository usuarioRepo;
	
	@Override
	public void guardar(Usuario usuario) {
		usuarioRepo.save(usuario);

	}

	@Override
	public void delete(int idusuario) {
		usuarioRepo.deleteById(idusuario);
	}

	@Override
	public List<Usuario> findAllUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario buscarPorId(int idusuario) {
		// TODO Auto-generated method stub
		return null;
	}

}
