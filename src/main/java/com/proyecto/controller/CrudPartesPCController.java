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

import com.proyecto.entidad.PartesPC;
import com.proyecto.service.PartesPCService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("url/crudPartesPC")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class CrudPartesPCController {
	
	@Autowired
	private PartesPCService service;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<PartesPC>> listaPartesPC(){
		return ResponseEntity.ok(service.listaPartes());
	}
	
	@GetMapping("/listadoxTipo/{idtipo}")
	@ResponseBody
	public ResponseEntity<List<PartesPC>> listaxTipo(@PathVariable("idtipo")int idtipo){
		return ResponseEntity.ok(service.listaPartesPorTipo(idtipo));
	}
	
	@PostMapping("/registroPartesPC")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> registraPartesPC(@RequestBody PartesPC obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setId_partes(0);
			PartesPC objSalida = service.insertaActualizaPartesPC(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error en el registro de partes de PC");
			} else {
				salida.put("mensaje", "Registro exitoso con el Id: " + obj.getId_partes());
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error en el registro");
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);
	}
	
	@PostMapping("/actualizaPartesPC")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaPartesPC(@RequestBody PartesPC obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			PartesPC objSalida = service.insertaActualizaPartesPC(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error al actualizar");
			} else {
				salida.put("mensaje", "Actualizacion exitosa con el Id: " + obj.getId_partes());
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error al actualizar");
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);
	}
	
	@DeleteMapping("/eliminarPartesPC/{idpartes}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaPartesPC(@PathVariable("idpartes")int idpartes){
		Map<String, Object> salida = new HashMap<>();
		try {
			Optional<PartesPC> opt = service.buscaPartes(idpartes);
			if (opt.isPresent()) {
				service.eliminarPartesPC(idpartes);
				Optional<PartesPC> optSalida = service.buscaPartes(idpartes);
				if (optSalida.isEmpty()) {
					salida.put("mensaje", "Registro eliminado con exito");
				}else {
					salida.put("mensaje", "No se pudo eliminar el registro");
				}
			}else {
				salida.put("mensaje", "No existe el id deseado");
			}
			
		} catch (Exception e) {
			salida.put("mensaje", "No se pudo eliminar el registro");
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);
	}

}
