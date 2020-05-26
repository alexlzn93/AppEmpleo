package com.alexlzn.service;

import java.util.List;
import java.util.Optional;

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
		System.out.println("Guardando usuario: " + usuario.getNombre());
	}

	@Override
	public void delete(Integer idusuario) {
		usuarioRepo.deleteById(idusuario);
		System.out.println("Eliminando usuario con id "+ idusuario);
	}

	@Override
	public List<Usuario> findAllUsuarios() {
		System.out.println("Lista de todos los usuarios");
		return (List<Usuario>) usuarioRepo.findAll();
	}

	@Override
	public Usuario buscarPorId(Integer idusuario) {
		System.out.println("Buscando usuario con id " + idusuario);
		Optional<Usuario>user= usuarioRepo.findById(idusuario);
		if(user.isPresent()) {
			user.get();
		}
		System.out.println("No se ha encontrado el usuario con id: " +idusuario);
		return null;
	}

	@Override
	public Usuario findByUsername(String username) {
		System.out.println("USERNAME " + username);
		return usuarioRepo.findByUsername(username);
	}

}
