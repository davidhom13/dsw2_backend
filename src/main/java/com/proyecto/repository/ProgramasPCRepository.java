package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.entidad.ProgramasPC;

@Repository
public interface ProgramasPCRepository extends JpaRepository<ProgramasPC, Integer>{

	@Query(value = "select * from tb_programa p where p.idCatePrograma = ?1", nativeQuery = true)
	public List<ProgramasPC> listaProgramasPorCategoria(int idCatePrograma);
	
}
