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


import com.proyecto.entidad.Programa;
import com.proyecto.service.ProgramaService;
import com.proyecto.util.AppSettings;



@RestController
@RequestMapping("/url/crudPrograma")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)


public class CrudProgramaController {
	
	@Autowired
	private ProgramaService programaService;

	@GetMapping("/listaProgramaPorNombreLike/{filtro}")
	@ResponseBody
	public ResponseEntity<List<Programa>> consulta(@PathVariable("filtro")String filtro){
		 List<Programa> salida = null;
		try {
			if (filtro.equals("todos")) {
				salida = programaService.listaProgramaPorNombreLike("%");
			}else {
			salida = programaService.listaProgramaPorNombreLike("%"+filtro+"%");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);
		}

	@PostMapping("/registraPrograma")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaPrograma(@RequestBody Programa obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setId_programa(0);
			Programa objSalida = programaService.insertaActualizaPrograma(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error en el registro ");
			}else {
				salida.put("mensaje", "Registro exitoso con el Id: " + obj.getNom_prog());
			}
		}catch(Exception e){
			salida.put("mensaje",  "Error en el registro ");
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);
	}
	@PutMapping("/actualizaPrograma")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaPrograma(@RequestBody Programa obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Programa objSalida = programaService.insertaActualizaPrograma(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error al actualizar ");
			}else {
				salida.put("mensaje", "Actualizacion exitoso con el Id: " + obj.getNom_prog());
			}
		}catch(Exception e){
			salida.put("mensaje", "Error al actualizar ");
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);
	}
	@DeleteMapping("/eliminaCliente/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaPrograma(@PathVariable("id")int id) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Optional<Programa> opt = programaService.buscaPrograma(id);
			if (opt.isPresent()) {
				programaService.eliminaPrograma(id);
			Optional<Programa> optCliente = programaService.buscaPrograma(id);
			if (optCliente.isEmpty()) {
				salida.put("mensaje","Registro eliminado con exito");
			}else {
				salida.put("mensaje", "No se pudo eliminar el registro");
			}
			}else {
				salida.put("mensaje",  "No existe el id deseado");	
			}
		}catch(Exception e){
			salida.put("mensaje", "No se pudo eliminar el registro");
		}
		return ResponseEntity.ok(salida);
	}
}

