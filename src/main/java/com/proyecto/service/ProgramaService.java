package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.Programa;

public interface ProgramaService {

	public abstract List<Programa> listaPrograma();
	public abstract Programa InsertaActualizaPrograma(Programa obj);
	public List<Programa> listaProgramaPorIdCategoriaNombrePrecio(int id_programa, int id_categoria, String nom_prog, String precio);
	public abstract List<Programa> listaProgramaPorNombreLike(String nom_prog);
	public abstract void eliminaPrograma(int id_programa);
	public abstract Optional<Programa> buscaPrograma(int id_programa);
}
