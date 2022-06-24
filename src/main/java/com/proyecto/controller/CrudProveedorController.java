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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Proveedor;
import com.proyecto.service.ProveedorService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/crudProveedor")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class CrudProveedorController {
	
	@Autowired
	private ProveedorService proveedorService;
	
	@GetMapping("/listaProveedorPorRazonLike/{filtro}")
	@ResponseBody
	public ResponseEntity<List<Proveedor>> consulta(@PathVariable("filtro")String filtro){
		List<Proveedor> salida = null;
		try {
			if (filtro.equals("todos")) {
				salida = proveedorService.listaProveedorPorRazonLike("%");
			}else {
				salida = proveedorService.listaProveedorPorRazonLike("%"+filtro+"%");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);
	}

	@PostMapping("/registraProveedor")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaDocente(@RequestBody Proveedor obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdProveedor(0);
			Proveedor objSalida = proveedorService.insertaActualizaProveedor(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error en el registro ");
			} else {
				salida.put("mensaje", "Registro exitoso con el Id: " + obj.getIdProveedor());
			}
		} catch (Exception e) {
			salida.put("mensaje",  "Error en el registro ");
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);

	}
	
	@PutMapping("/actualizaProveedor")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaProveedor(@RequestBody Proveedor obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Proveedor objSalida = proveedorService.insertaActualizaProveedor(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error al actualizar ");
			} else {
				salida.put("mensaje", "Actualizacion exitoso con el Id: " + obj.getIdProveedor());
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error al actualizar ");
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);
	}
	
	@DeleteMapping("/eliminaProveedor/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> elimina(@PathVariable("id")int id) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Optional<Proveedor> opt = proveedorService.buscaProveedor(id);
			if (opt.isPresent()) {
				proveedorService.eliminaProveedor(id);
				Optional<Proveedor> optSalida = proveedorService.buscaProveedor(id);
				if (optSalida.isEmpty()) {
					salida.put("mensaje","Registro eliminado con exito");
				}else {
					salida.put("mensaje","No se pudo eliminar el registro");
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
