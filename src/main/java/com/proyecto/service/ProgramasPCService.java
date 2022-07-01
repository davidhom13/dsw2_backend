package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.ProgramasPC;

public interface ProgramasPCService {
	
	public abstract List<ProgramasPC> listaProgramas();
	public abstract List<ProgramasPC> listaProgramasPorCategoria(int idCatePrograma);
	public abstract ProgramasPC insertaActualizaProgramas(ProgramasPC obj);
	public abstract void eliminarProgramas(int id_programa);
	public abstract Optional<ProgramasPC> buscaProgramas(int id_programa);

}
