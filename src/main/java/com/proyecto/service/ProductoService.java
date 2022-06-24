package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.Producto;

public interface ProductoService {
	
	public abstract List<Producto> listaProducto();
	public abstract List<Producto> listaProductoPorSerie(String serie);
	public abstract Producto RegistraProducto(Producto obj);
	public abstract List<Producto> listaProductoPorNombreSeriePaisMarcaEstado(String nombre, String serie, int idPais, int idMarca, int estado);

	public abstract List<Producto> listaProductoPorNombre(String nombre);
	public abstract Producto insertaActualizaProducto(Producto obj);
	public abstract void eliminarProducto(int idProducto);
	public abstract Optional<Producto> buscaProducto(int idProducto);
}
