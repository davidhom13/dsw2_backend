package com.proyecto.service;

import com.proyecto.entidad.Sede;
import java.util.List;
import java.util.Optional;

public interface SedeService {

    Sede RegistrarSede(Sede obj);

    List<Sede> listaSede();

    List<Sede> listaSedexNombrePaisEstado(String nombre, String codigoPostal, int idPais, int estado);

    List<Sede> listaSedexNombre(String nombre);

    Sede insertaActualizaSede(Sede obj);

    void eliminaSede(int idSede);

    Optional<Sede> buscaSede(int idSede);
}
