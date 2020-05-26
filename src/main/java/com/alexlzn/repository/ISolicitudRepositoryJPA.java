package com.alexlzn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexlzn.model.Solicitud;

public interface ISolicitudRepositoryJPA extends JpaRepository<Solicitud, Integer> {

}
