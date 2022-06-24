package com.proyecto.repository;

import com.proyecto.entidad.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SedeRepository extends JpaRepository<Sede, Integer> {

    @Query(value ="Select * from sede", nativeQuery = true)
    List<Sede> listaSede();

    @Query("Select s from Sede s where (?1 is '' or s.nombre like %?1%) and (?2 is '' or s.codigoPostal = ?2) and (?3 is -1 or s.pais.idPais = ?3) and s.estado = ?4")
    List<Sede> listaSedexNombrePaisEstado(String nombre, String codigoPostal, int idPais, int estado);

    @Query("select s from Sede s where s.nombre like ?1")
    List<Sede> listaSedexNombre(String nombre);

}
