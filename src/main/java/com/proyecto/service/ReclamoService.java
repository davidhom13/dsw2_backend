package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Cliente;
import com.proyecto.entidad.Reclamo;
import com.proyecto.entidad.Sede;

public interface ReclamoService {
	
	Reclamo RegistraReclamo(Reclamo obj);

	List<Reclamo> listaReclamoxDescripcionEstadoClienteTipo(String descripcion, int estado, int idCliente, int idTipoReclamo);

	List<Reclamo> listaReclamoPorDescripcionLike(String descripcion);

	Reclamo InsertaActualizaReclamo(Reclamo obj);
	
	void eliminaReclamo(int id);
}
