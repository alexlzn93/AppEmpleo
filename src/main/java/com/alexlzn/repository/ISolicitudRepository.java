package com.alexlzn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alexlzn.model.Solicitud;
@Repository
public interface ISolicitudRepository extends CrudRepository<Solicitud, Integer> {

}
