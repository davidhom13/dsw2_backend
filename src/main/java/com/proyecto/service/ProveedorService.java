package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.Proveedor;

public interface ProveedorService {
	
	public abstract List<Proveedor> listaProveedor();
	public abstract List<Proveedor> listaProveedorPorRuc(String ruc);
	public abstract Proveedor RegistraProveedor(Proveedor obj);
	public abstract List<Proveedor>listaProveedorPorRucRazonNombreUbigeo(String razonsocial, String ruc,int idUbigeo, int estado);

	public abstract List<Proveedor>listaProveedorPorRazonLike(String razonsocial);
	public abstract Proveedor insertaActualizaProveedor(Proveedor obj);
	
	public abstract void eliminaProveedor(int idProveedor);
	public abstract Optional<Proveedor> buscaProveedor(int idProveedor);
}
