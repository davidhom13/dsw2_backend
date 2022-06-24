package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Cliente;
import com.proyecto.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Override
	public List<Cliente> listaCliente() {
		return repository.findAll();
	}
	
	@Override
	public Cliente InsertaActualizaCliente(Cliente obj) {
		// TODO Auto-generated method stub
		return repository.save(obj);
	}
	
	@Override
	public List<Cliente> listaClientePorDni(String dni) {
		// TODO Auto-generated method stub
		return repository.listaClientePorDni(dni);
	}

	
	@Override
	public List<Cliente> listaClientePorNombreDniUbigeo(String nombres, String apellidos, String dni, int idUbigeo, int estado) {
		return repository.listaDocentePorNombreDniUbigeo(nombres, apellidos, dni, idUbigeo, estado);
	}

	@Override
	public Cliente insertaActualizaCliente(Cliente cliente) {
		return repository.save(cliente);
		
	}

	@Override
	public List<Cliente> listaClientePorNombreLike(String nombres) {
		return repository.listaPorNombreLike(nombres);
	}

	
/////////////crud cliente3////////

@Override
public void eliminaCliente(int id) {
repository.deleteById(id);

}


@Override
public Cliente buscaClientePorId(int idCliente) {
return repository.buscaClientePorId(idCliente);
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	////////////
	
	
	/*
	@Override
	public List<Cliente> listaClientePorNombreDniUbigeo(String nombre, String dni, int idUbigeo) {
		return repository.listaClientePorNombreDniUbigeo(nombres, dni, idUbigeo);
	}
	*/
	/////////////////
	
	
	
	
	
	
	
	

}
