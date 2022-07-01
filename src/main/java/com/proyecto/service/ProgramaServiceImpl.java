package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Programa;
import com.proyecto.repository.ProgramaRepository;

@Service
public class ProgramaServiceImpl implements ProgramaService{
	
	@Autowired
	private ProgramaRepository repository;

	@Override
	public List<Programa> listaPrograma() {
		return repository.findAll();
	}
		@Override
	public  Programa insertaActualizaPrograma(Programa obj) {
			return repository.save(obj);
		}
		/*@Override
		public List<Programa> listaProgramaPorIdCategoriaNombrePrecio(int id_programa, int id_categoria, String nom_prog, String precio) {
			return repository.listaProgramaPorIdCategoriaNombrePrecio(id_programa, id_categoria, nom_prog, precio);
		}*/
		@Override
		public List<Programa> listaProgramaPorNombreLike(String nom_prog) {
			return repository.listaProgramaPorNombreLike(nom_prog);
		}

		@Override
		public void eliminaPrograma(int id_programa) {
			repository.deleteById(id_programa);
			
		}

		@Override
		public Optional<Programa> buscaPrograma(int id_programa) {
			return repository.findById(id_programa);
		}
		@Override
		public List<Programa> listaProgramaPorId(String id_programa) {
			return repository.listaProgramaPorId(id_programa);
		}
		@Override
		public Programa RegistraPrograma(Programa obj) {
			return repository.save(obj);
		}
}
