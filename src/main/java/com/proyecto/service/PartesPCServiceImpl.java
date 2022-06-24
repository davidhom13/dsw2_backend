package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.PartesPC;
import com.proyecto.repository.PartesPCRepository;

@Service
public class PartesPCServiceImpl implements PartesPCService{

	@Autowired
	private PartesPCRepository repository;
	
	@Override
	public List<PartesPC> listaPartes() {
		return repository.findAll();
	}

	@Override
	public List<PartesPC> listaPartesPorTipo(int id_tipopartes) {
		return repository.listaPartesPorTipo(id_tipopartes);
	}

	@Override
	public PartesPC insertaActualizaPartesPC(PartesPC obj) {
		return repository.save(obj);
	}

	@Override
	public void eliminarPartesPC(int id_partes) {
		repository.deleteById(id_partes);
	}

	@Override
	public Optional<PartesPC> buscaPartes(int id_partes) {
		return repository.findById(id_partes);
	}

}
