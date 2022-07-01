package com.proyecto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/url/tb_programa")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ProgramaController {
	
	@Autowired
	private ProgramaService programaService;

	
	@GetMapping("/listaPrograma")
	@ResponseBody
	public ResponseEntity<List<Programa>> listaPrograma(){
		List<Programa> lista = programaService.listaPrograma();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public  ResponseEntity<Map<String, Object>> insertaPrograma(@RequestBody Programa obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			Programa objSalida = programaService.insertaActualizaPrograma(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			}else {
				salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	@GetMapping("/listaProgramaConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaProgramaNombreDniUbigeo(
			@RequestParam(name = "id_programa", required = false, defaultValue = "") int id_programa,
			@RequestParam(name = "id_categoria", required = false, defaultValue = "") int id_categoria,
			@RequestParam(name = "nom_prog", required = false, defaultValue = "") String nom_prog,
			@RequestParam(name = "precio", required = false, defaultValue = "") String precio) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Programa> lista = programaService.listaProgramaPorIdCategoriaNombrePrecio(id_programa, id_categoria, nom_prog, precio);
			if(CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}

}
