package com.proyecto.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Programa;
import com.proyecto.service.ProgramaService;
import com.proyecto.util.AppSettings;
import com.proyecto.util.Constantes;


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
			Programa objSalida = programaService.InsertaActualizaPrograma(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			}else {
				salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
			}
		}catch(Exception e){
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);
	}
	@PutMapping("/actualizaPrograma")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaPrograma(@RequestBody Programa obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Programa objSalida = programaService.InsertaActualizaPrograma(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
			}else {
				salida.put("mensaje", Constantes.MENSAJE_ACT_EXITOSO);
			}
		}catch(Exception e){
			salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);
	}
}
