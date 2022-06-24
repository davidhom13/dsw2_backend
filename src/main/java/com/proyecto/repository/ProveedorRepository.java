package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{
	
	@Query(value = "Select * from proveedor e where e.ruc = ?1", nativeQuery = true)
	public abstract List<Proveedor> listaProveedorPorRuc(String ruc);
	
	@Query("select p from Proveedor p where (?1 is '' or p.razonsocial like %?1%) and (?2 is '' or p.ruc = ?2) and (?3 is -1 or p.ubigeo.idUbigeo = ?3) and p.estado = ?4")    
	public abstract List<Proveedor>listaProveedorPorRucRazonNombreUbigeo(String razonsocial, String ruc,int idUbigeo, int estado);
	

	@Query("select p from Proveedor p where p.razonsocial like ?1")
	public List<Proveedor> listaProveedorPorRazonLike(String razonsocial);
}
