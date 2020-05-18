package com.alexlzn.interfaces;

import java.util.List;

import com.alexlzn.model.Solicitud;

public interface ISolicitudService {

	public List<Solicitud> findAllSolicitud(); //lista de solicitudes
	public void guardar(Solicitud solicitud);
	public void eliminar( Integer idSolicitud);
	public Solicitud buscarPorId( Integer idSolicitud);
}
