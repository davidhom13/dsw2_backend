package com.proyecto.service;

import com.proyecto.entidad.Sede;
import com.proyecto.repository.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SedeServiceImpl implements SedeService{

    @Autowired
    private SedeRepository repository;

    //Registrar
    @Override
    public Sede RegistrarSede(Sede obj) {
        return repository.save(obj);
    }

    @Override
    public List<Sede> listaSede() {
        return repository.listaSede();
    }

    @Override
    public List<Sede> listaSedexNombrePaisEstado(String nombre, String codigoPostal, int idPais, int estado) {
        return repository.listaSedexNombrePaisEstado(nombre, codigoPostal, idPais, estado);
    }

    @Override
    public List<Sede> listaSedexNombre(String nombre) {
        return repository.listaSedexNombre(nombre);
    }


    @Override
    public Sede insertaActualizaSede(Sede obj) {
        return repository.save(obj);
    }

    @Override
    public void eliminaSede(int idSede) {
        repository.deleteById(idSede);
    }

    @Override
    public Optional<Sede> buscaSede(int idSede) {
        return repository.findById(idSede);
    }


}
