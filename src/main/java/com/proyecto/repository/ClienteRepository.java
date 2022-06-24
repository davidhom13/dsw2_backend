package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	
	
	@Query("Select e from Cliente e where e.dni = ?1")
	public abstract List<Cliente> listaClientePorDni(String dni);

	////cosulta///////
	
	@Query("select x from Cliente x where (?1 is '' or x.nombres like ?1)and (?2 is '' or x.apellidos like ?2)  and (?3 is '' or x.dni = ?3) and (?4 is -1 or x.ubigeo.idUbigeo = ?4) and x.estado = ?5")
	public List<Cliente> listaDocentePorNombreDniUbigeo(String nombres, String apellidos , String dni, int idUbigeo, int estado);
	
	
	
	/////////crud///////
	
	@Query("select x from Cliente x where x.nombres like ?1")
	public List<Cliente> listaPorNombreLike(String nombre);
	
	
	
	

	////////crud elimiar34 //////
	
	
	
	@Query("select x from Cliente x where x.idCliente = ?1")
	public Cliente buscaClientePorId(int idCliente);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
