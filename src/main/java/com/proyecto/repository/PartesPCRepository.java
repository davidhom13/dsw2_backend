package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.entidad.PartesPC;

@Repository
public interface PartesPCRepository extends JpaRepository<PartesPC, Integer>{

	@Query(value = "select * from tb_partesPC p where p.id_tipopartes = ?1", nativeQuery = true)
	public List<PartesPC> listaPartesPorTipo(int id_tipopartes);
	
}
