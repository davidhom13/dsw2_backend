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
import com.proyecto.entidad.ProgramasPC;
import com.proyecto.service.ProgramasPCService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/crudProgramasPC")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class CrudProgramasPCController {
	
	@Autowired
	private ProgramasPCService service;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<ProgramasPC>> listaProgramasPC(){
		return ResponseEntity.ok(service.listaProgramas());
	}
	
	@GetMapping("/listadoxCategoria/{idCatePrograma}")
	@ResponseBody
	public ResponseEntity<List<ProgramasPC>> listaxCategoria(@PathVariable("idCatePrograma")int idCatePrograma){
		List<ProgramasPC> salida = null;
		try {
			if (idCatePrograma==-1) {
				salida = service.listaProgramas();
			} else {
				salida = service.listaProgramasPorCategoria(idCatePrograma);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);
	}
	
	@PostMapping("/registroProgramasPC")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> registrarProgramasPC(@RequestBody ProgramasPC obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setId_programa(0);
			ProgramasPC objSalida = service.insertaActualizaProgramas(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error en el registro de programas de PC");
			} else {
				salida.put("mensaje", "Registro exitoso con el Id: " + obj.getId_programa());
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error en el registro");
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);
	}
	
	@PostMapping("/actualizaProgramasPC")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaProgramasPC(@RequestBody ProgramasPC obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			ProgramasPC objSalida = service.insertaActualizaProgramas(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error al actualizar");
			} else {
				salida.put("mensaje", "Actualizacion exitosa con el Id: " + obj.getId_programa());
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error al actualizar");
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);
	}
	
	@DeleteMapping("/eliminarProgramasPC/{id_programa}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaProgramasPC(@PathVariable("id_programa")int id_programa){
		Map<String, Object> salida = new HashMap<>();
		try {
			Optional<ProgramasPC> opt = service.buscaProgramas(id_programa);
			if (opt.isPresent()) {
				service.eliminarProgramas(id_programa);
				Optional<ProgramasPC> optSalida = service.buscaProgramas(id_programa);
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
