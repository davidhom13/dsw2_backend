package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Producto;
import com.proyecto.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private ProductoRepository repository;
	
	@Override
	public List<Producto> listaProducto() {
		return repository.findAll();
	}
	@Override
	public List<Producto> listaProductoPorSerie(String serie) {
		return repository.listaProductPorSerie(serie);
	}
	@Override
	public Producto RegistraProducto(Producto obj) {
		return repository.save(obj);
	}
	@Override
	public List<Producto> listaProductoPorNombreSeriePaisMarcaEstado(String nombre, String serie, int idPais,
			int idMarca, int estado) {
		return repository.listaProductoPorNombreSeriePaisMarcaEstado(nombre, serie, idPais, idMarca, estado);
	}
	@Override
	public List<Producto> listaProductoPorNombre(String nombre) {
		return repository.listaProductoPorNombre(nombre);
	}
	@Override
	public Producto insertaActualizaProducto(Producto obj) {
		return repository.save(obj);
	}
	@Override
	public void eliminarProducto(int idProducto) {
		repository.deleteById(idProducto);
	}
	@Override
	public Optional<Producto> buscaProducto(int idProducto) {
		return repository.findById(idProducto);
	}
}
