package com.alexlzn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexlzn.model.Vacante;

public interface IVacanteRepositoryJPA extends JpaRepository<Vacante, Integer> {

}
