package com.alexlzn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alexlzn.model.Usuario;
@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario, Integer> {

	public Usuario findByUsername(String username);
}
