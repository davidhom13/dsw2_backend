package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Proveedor;
import com.proyecto.service.ProveedorService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/proveedor")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ProveedorController {
	//Autor : Tomas Vega
	
	@Autowired
	private ProveedorService service;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Proveedor>>listaProveedor(){
		return ResponseEntity.ok(service.listaProveedor());
	}
	
	@GetMapping("/{ruc}")
	@ResponseBody
	public ResponseEntity<List<Proveedor>>listaporRuc(@PathVariable("ruc") String ruc){
		return ResponseEntity.ok(service.listaProveedorPorRuc(ruc));
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> RegistraProveedor(@RequestBody Proveedor obj){

		HashMap<String, Object> salida = new HashMap<String, Object>();
		
		try {
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			
			List<Proveedor> lista = service.listaProveedorPorRuc(obj.getRuc());
			if(CollectionUtils.isEmpty(lista)) {
				Proveedor objsalida =service.RegistraProveedor(obj);
				if(objsalida==null) {
					salida.put("mensaje", "Error en el registro ");
				} else {
					salida.put("mensaje", "Registro exitoso con el Id: " + obj.getIdProveedor());
				}
				
			} else {
				salida.put("mensaje", "Proveedor ya existe :" + obj.getIdProveedor());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en el registro "+e.getMessage());
		} 

		return ResponseEntity.ok(salida);

	}
		
	
	@GetMapping("/listaProveedorConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaProveedorRazonRucUbigeoEstado(
			@RequestParam(name = "razonsocial", required = false, defaultValue = "") String razonsocial,
			@RequestParam(name = "ruc", required = false, defaultValue = "") String ruc,
			@RequestParam(name = "idUbigeo", required = false, defaultValue = "-1") int idUbigeo,
			@RequestParam(name = "estado", required = true, defaultValue="1") int estado)
	{
		Map<String, Object> salida = new HashMap<>();

		try {
			List<Proveedor> lista = service.listaProveedorPorRucRazonNombreUbigeo(razonsocial, ruc, idUbigeo,estado);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " para mostrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en el registro "+e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}
}
