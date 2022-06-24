package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Reclamo;

public interface ReclamoRepository extends JpaRepository<Reclamo, Integer>{
    @Query("Select r from Reclamo r where (?1 is '' or r.descripcion like %?1%) and r.estado = ?2 and (?3 is -1 or r.cliente.idCliente = ?3) and (?4 is -1 or r.tipoReclamo.idTipoReclamo = ?4)")
    List<Reclamo> listaReclamoxDescripcionEstadoClienteTipo(String descripcion, int estado, int idCliente, int idTipoReclamo);
    
    @Query("Select r from Reclamo r where (?1 is '' or r.descripcion like %?1%)")
    List<Reclamo> listaReclamoPorDescripcionLike(String descripcion);
}
