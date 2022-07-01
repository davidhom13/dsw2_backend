package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Programa;


public interface ProgramaRepository extends JpaRepository<Programa, Integer> {
	
	@Query(value = "Select * from tb_programa e where e.id_programa = ?1", nativeQuery = true)
	public abstract List<Programa> listaProgramaPorId(String ruc);
	

	@Query(value = "select x from tb_programa x where (?1 is '' or x.id_programa like ?1) and (?2 is '' or x.id_categoria like ?2) and (?3 is '' or x.nom_prog = ?3) and (?4 is -1 or x.precio = ?4)", nativeQuery = true)         
	public List<Programa> listaProgramaPorIdCategoriaNombrePrecio(int id_programa, int id_categoria, String nom_prog, String precio);

	
	@Query(value = "select x from tb_programa x where x.nom_prog like ?1", nativeQuery = true)
	public List<Programa> listaProgramaPorNombreLike(String nom_prog);
	

}
