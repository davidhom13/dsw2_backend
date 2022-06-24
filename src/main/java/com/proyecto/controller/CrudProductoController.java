package com.proyecto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Producto;
import com.proyecto.service.ProductoService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("url/crudProducto")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class CrudProductoController {

	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/listaProductoxNombre/{filtro}")
	@ResponseBody
	public ResponseEntity<List<Producto>> consulta(@PathVariable("filtro")String filtro){
		List<Producto> salida = null;
		try {
			if (filtro.equals("todos")) {
				salida = productoService.listaProductoPorNombre("%");
			}else {
				salida = productoService.listaProductoPorNombre("%"+filtro+"%");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);
	}
	
	@PostMapping("/registrarProducto")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> registraProducto(@RequestBody Producto obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdProducto(0);
			Producto objSalida = productoService.insertaActualizaProducto(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error en el registro");
			} else {
				salida.put("mensaje", "Registro exitoso con el Id: " + obj.getIdProducto());
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error en el registro");
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);
	}
	
	@PostMapping("/actualizaProducto")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaProducto(@RequestBody Producto obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			Producto objSalida = productoService.insertaActualizaProducto(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error al actualizar");
			} else {
				salida.put("mensaje", "Actualizacion exitoso con el Id: " + obj.getIdProducto());
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error al actualizar");
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);
	}
	
	@DeleteMapping("/eliminaProducto/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> elimina(@PathVariable("id")int id){
		Map<String, Object> salida = new HashMap<>();
		try {
			Optional<Producto> opt = productoService.buscaProducto(id);
			if (opt.isPresent()) {
				productoService.eliminarProducto(id);
				Optional<Producto> optSalida = productoService.buscaProducto(id);
				if (optSalida.isEmpty()) {
					salida.put("mensaje", "Registro eliminado con exito");
				}else {
					salida.put("mensaje", "No se pudo eliminar el registro");
				}
			}else {
				salida.put("mensaje", "No existe el id deseado");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se pudo eliminar el registro");
		}
		return ResponseEntity.ok(salida);
	}
	
}
