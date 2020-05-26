package com.alexlzn.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alexlzn.model.Solicitud;
import com.alexlzn.model.Vacante;

public interface ISolicitudService {

	public List<Solicitud> findAllSolicitud(); //lista de solicitudes
	public void guardar(Solicitud solicitud);
	public void eliminar( Integer idSolicitud);
	public Solicitud buscarPorId( Integer idSolicitud);
	public Page<Solicitud> findAllSolicitudesPaginacion(Pageable page);
}
