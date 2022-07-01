package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.CategoriaPrograma;
import com.proyecto.entidad.TipoPartes;
import com.proyecto.service.CategoriaService;
import com.proyecto.service.TipoPartesService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/util")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class UtilController {
	
	@Autowired
	private TipoPartesService tipoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/listaTipo")
	@ResponseBody
	public List<TipoPartes> listaTipo() {
		return tipoService.listaTipoPartes();
	}
	
	@GetMapping("/listaCategoria")
	@ResponseBody
	public List<CategoriaPrograma> listaCategoria(){
		return categoriaService.listaCategoria();
	}

}
