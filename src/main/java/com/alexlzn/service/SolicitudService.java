package com.alexlzn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexlzn.interfaces.ISolicitudService;
import com.alexlzn.model.Solicitud;
import com.alexlzn.repository.ISolicitudRepository;
@Service
public class SolicitudService implements ISolicitudService {

	@Autowired
	ISolicitudRepository solicitudRepo;
	@Override
	public List<Solicitud> findAllSolicitud() {
		System.out.println("Lista de solicitudes");
		return (List<Solicitud>) solicitudRepo.findAll();
	}
	@Override
	public void guardar(Solicitud solicitud) {
		System.out.println("Guardando solicitud");
		solicitudRepo.save(solicitud);
		
	}
	@Override
	public void eliminar(Integer idSolicitud) {
		Solicitud solicitud= new Solicitud();
		System.out.println("Eliminando solicitud " + "ID: " + idSolicitud  );
		solicitudRepo.deleteById(idSolicitud);
	}
	@Override
	public Solicitud buscarPorId(Integer idSolicitud) {
		Optional<Solicitud> solicitud=solicitudRepo.findById(idSolicitud);
		if(solicitud.isPresent())
			return solicitud.get();
		else {
			System.out.println("No se a encontrado la solicitud con id: " + idSolicitud);
		}
		return null;
	}

}
