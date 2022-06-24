package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.TipoReclamo;


public interface TipoReclamoRepository extends JpaRepository<TipoReclamo, Integer>{

    @Query(value ="Select * from tipo_reclamo", nativeQuery = true)
    List<TipoReclamo> listaTipoReclamo();
}
