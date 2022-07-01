package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.ProgramasPC;
import com.proyecto.repository.ProgramasPCRepository;

@Service
public class ProgramasPCServiceImpl implements ProgramasPCService{

	@Autowired
	private ProgramasPCRepository repository;
	
	@Override
	public List<ProgramasPC> listaProgramas() {
		return repository.findAll();
	}

	@Override
	public List<ProgramasPC> listaProgramasPorCategoria(int idCatePrograma) {
		return repository.listaProgramasPorCategoria(idCatePrograma);
	}

	@Override
	public ProgramasPC insertaActualizaProgramas(ProgramasPC obj) {
		return repository.save(obj);
	}

	@Override
	public void eliminarProgramas(int id_programa) {
		repository.deleteById(id_programa);
	}

	@Override
	public Optional<ProgramasPC> buscaProgramas(int id_programa) {
		return repository.findById(id_programa);
	}

}
