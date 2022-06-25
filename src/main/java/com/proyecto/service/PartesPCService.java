package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.PartesPC;

public interface PartesPCService {
	
	public abstract List<PartesPC> listaPartes();
	public abstract List<PartesPC> listaPartesPorTipo(int id_tipopartes);
	public abstract PartesPC insertaActualizaPartesPC(PartesPC obj);
	public abstract void eliminarPartesPC(int id_partes);
	public abstract Optional<PartesPC> buscaPartes(int id_partes);
	
}
