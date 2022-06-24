package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.entidad.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	@Query(value = "Select * from producto e where e.serie = ?1", nativeQuery = true)
	public abstract List<Producto> listaProductPorSerie(String serie);
	
	@Query("select p from Producto p where (?1 is '' or p.nombre like %?1%) and (?2 is '' or p.serie = ?2) and (?3 is -1 or p.pais.idPais = ?3) and (?4 is -1 or p.marca.idMarca = ?4) and p.estado = ?5")
	public abstract List<Producto> listaProductoPorNombreSeriePaisMarcaEstado(String nombre, String serie,int idPais, int idMarca, int estado);

	@Query("select p from Producto p where p.nombre like ?1")
	public List<Producto> listaProductoPorNombre(String nombre);
	
}
