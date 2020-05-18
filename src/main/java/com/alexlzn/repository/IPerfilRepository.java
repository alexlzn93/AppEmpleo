package com.alexlzn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alexlzn.model.Perfil;
@Repository
public interface IPerfilRepository extends CrudRepository<Perfil, Integer> {

}
