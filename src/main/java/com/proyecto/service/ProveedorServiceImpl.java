package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Proveedor;
import com.proyecto.repository.ProveedorRepository;

@Service
public class ProveedorServiceImpl implements ProveedorService {
	
	@Autowired
	private ProveedorRepository repository;
	
	@Override
	public List<Proveedor> listaProveedor(){
		return repository.findAll();
	}

	@Override
	public Proveedor RegistraProveedor(Proveedor obj) {
		return repository.save(obj);
	}


	@Override
	public List<Proveedor> listaProveedorPorRuc(String ruc) {
		return repository.listaProveedorPorRuc(ruc);
	}

	@Override
	public List<Proveedor> listaProveedorPorRucRazonNombreUbigeo(String razonsocial, String ruc, int idUbigeo,
			int estado) {
		return repository.listaProveedorPorRucRazonNombreUbigeo(razonsocial, ruc, idUbigeo, estado);
	}

	@Override
	public List<Proveedor> listaProveedorPorRazonLike(String razonsocial) {
		return repository.listaProveedorPorRazonLike(razonsocial);
	}

	@Override
	public Proveedor insertaActualizaProveedor(Proveedor obj) {
		return repository.save(obj);
	}

	@Override
	public void eliminaProveedor(int idProveedor) {
		 repository.deleteById(idProveedor);
		
	}

	@Override
	public Optional<Proveedor> buscaProveedor(int idProveedor) {
		return repository.findById(idProveedor);
	}

}
