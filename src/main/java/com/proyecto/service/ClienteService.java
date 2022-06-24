package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Cliente;

public interface ClienteService{

	public abstract List<Cliente> listaCliente();
	
	public abstract List<Cliente> listaClientePorDni(String dni);
	
	public abstract Cliente InsertaActualizaCliente(Cliente obj);
	
	
	
	
	////consulta cliente///

	public List<Cliente> listaClientePorNombreDniUbigeo(String nombres, String apellidos, String dni, int idUbigeo, int estado);

	
	
	
	
/////////crud cliente2////////
	
	
	public abstract Cliente insertaActualizaCliente(Cliente cliente);
	public abstract List<Cliente> listaClientePorNombreLike(String nombres);
	
	///crud eliminar cliente2
	public abstract void eliminaCliente(int id);
	public abstract Cliente buscaClientePorId(int idCliente);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
