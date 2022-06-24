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

import com.proyecto.entidad.Producto;
import com.proyecto.service.ProductoService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/producto")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ProductoController {
	
	//Autor : David Ortiz
	
	@Autowired
	private ProductoService service;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Producto>>listaProducto(){
		return ResponseEntity.ok(service.listaProducto());
	}
	
	@GetMapping("/{serie}")
	@ResponseBody
	public ResponseEntity<List<Producto>>listaPorSerie(@PathVariable("serie") String serie){
		return ResponseEntity.ok(service.listaProductoPorSerie(serie));
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> RegistraProducto(@RequestBody Producto obj){

		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			List<Producto> lista = service.listaProductoPorSerie(obj.getSerie());
			if(CollectionUtils.isEmpty(lista)) {
				Producto objsalida =service.RegistraProducto(obj);
				if(objsalida==null) {
					salida.put("mensaje", "Error en el registro");
				} else {
					salida.put("mensaje", "Registro exitoso con el Id: " + obj.getIdProducto());
				}
			} else {
				salida.put("mensaje", "Producto ya existe :" + obj.getIdProducto());
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en el registro "+e.getMessage());
		} 
		
		return ResponseEntity.ok(salida);

	}
	
	
	@GetMapping("/listaProducto")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaProductoNombreSeriePaisMarcaEstado(
			@RequestParam(name = "nombre", required = false, defaultValue = "") String nombre,
			@RequestParam(name = "serie", required = false, defaultValue = "") String serie,
			@RequestParam(name = "idPais", required = false, defaultValue = "-1") int idPais,
			@RequestParam(name = "idMarca", required = false, defaultValue = "-1") int idMarca,
			@RequestParam(name = "estado", required = true, defaultValue="1") int estado){
		Map<String, Object> salida = new HashMap<>();

		try {
			List<Producto> lista = service.listaProductoPorNombreSeriePaisMarcaEstado(nombre, serie, idPais, idMarca, estado);
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
