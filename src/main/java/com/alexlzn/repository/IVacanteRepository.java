package com.alexlzn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alexlzn.model.Vacante;
@Repository
public interface IVacanteRepository extends CrudRepository<Vacante, Integer> {

}
