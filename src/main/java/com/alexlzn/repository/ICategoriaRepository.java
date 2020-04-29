package com.alexlzn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alexlzn.model.Categoria;
@Repository
public interface ICategoriaRepository extends CrudRepository<Categoria,Integer> {

}
