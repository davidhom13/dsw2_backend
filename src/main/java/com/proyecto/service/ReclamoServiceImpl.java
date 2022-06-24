package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Cliente;
import com.proyecto.entidad.Reclamo;
import com.proyecto.entidad.Sede;
import com.proyecto.repository.ReclamoRepository;

@Service
public class ReclamoServiceImpl implements ReclamoService {
	
	@Autowired
	private ReclamoRepository repository;

	@Override
	public Reclamo RegistraReclamo(Reclamo obj) {
		return repository.save(obj);
	}
	
	@Override
	public List<Reclamo> listaReclamoxDescripcionEstadoClienteTipo(String descripcion, int estado, int idCliente, int idTipoReclamo) {
	    return repository.listaReclamoxDescripcionEstadoClienteTipo(descripcion, estado, idCliente, idTipoReclamo);
	}
	@Override
	public List<Reclamo> listaReclamoPorDescripcionLike(String descripcion) {
	    return repository.listaReclamoPorDescripcionLike(descripcion);
	}
	@Override
	public Reclamo InsertaActualizaReclamo(Reclamo obj) {
		// TODO Auto-generated method stub
		return repository.save(obj);
	}
	@Override
	public void eliminaReclamo(int id) {
	repository.deleteById(id);

	}

}
