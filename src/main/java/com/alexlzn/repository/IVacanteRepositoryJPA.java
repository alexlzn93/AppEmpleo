package com.alexlzn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexlzn.model.Vacante;
@Repository
public interface IVacanteRepositoryJPA extends JpaRepository<Vacante, Integer> {

}
