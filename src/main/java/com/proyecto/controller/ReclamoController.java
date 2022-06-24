package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.proyecto.entidad.Cliente;
import com.proyecto.entidad.Reclamo;
import com.proyecto.entidad.Sede;
import com.proyecto.service.ReclamoService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/reclamo")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ReclamoController {
	@Autowired
	private ReclamoService service;
	@PostMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> RegistraReclamo(@RequestBody Reclamo obj){

		HashMap<String, Object> salida = new HashMap<String, Object>();
		
		try {
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			
				Reclamo objsalida =service.RegistraReclamo(obj);
				if(objsalida==null) {
					salida.put("mensaje", "Error en el registro ");
				} else {
					salida.put("mensaje", "Registro exitoso con el Id: " + obj.getIdReclamo());
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en el registro "+e.getMessage());
		} 

		return ResponseEntity.ok(salida);

	}
	@PutMapping("/actualizaReclamo")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> actualizaCliente(@RequestBody Reclamo obj){
		HashMap<String, Object> salida = new HashMap<>();
		try {
				Reclamo objSalida = service.InsertaActualizaReclamo(obj);
				if (objSalida == null) {
					salida.put("mensaje", "Error en el actualizacion " );
				}else {
					salida.put("mensaje", "actualizacion exitosa " );
				}	
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en al actualizar " + e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/listaReclamoConParametros")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listaReclamoxDescripcionEstadoClienteTipo(
            @RequestParam(name = "descripcion", required = false, defaultValue = "") String descripcion,
            @RequestParam(name = "estado", required = true, defaultValue = "-1") int estado,
            @RequestParam(name = "idCliente", required = false, defaultValue = "-1") int idCliente,
            @RequestParam(name = "idTipoReclamo", required = false, defaultValue = "1") int idTipoReclamo) {
        Map<String, Object> salida = new HashMap<>();
        try {
            List<Reclamo> lista = service.listaReclamoxDescripcionEstadoClienteTipo("%"+descripcion+"%", estado, idCliente, idTipoReclamo);
            if (CollectionUtils.isEmpty(lista)) {
                salida.put("mensaje", "No existen datos para mostrar");
            }else {
                salida.put("lista", lista);
                salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
            }
        } catch (Exception e) {
            e.printStackTrace();
            salida.put("mensaje", "Error en la busqueda " +e.getMessage());
        }
        return ResponseEntity.ok(salida);
    }
	
	@GetMapping("/listaReclamoPorDescripcionLike/{descripcion}")
    @ResponseBody
	public ResponseEntity<List<Reclamo>> listaReclamoPorDescripcionLike(@PathVariable("descripcion") String descripcion) {
        List<Reclamo> lista = null;
        try {
            if (descripcion.equals("todos")) {
				lista = service.listaReclamoPorDescripcionLike("%");
			}else {
				lista = service.listaReclamoPorDescripcionLike("%" + descripcion + "%");	
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(lista);
    }
	
	@DeleteMapping("/eliminaReclamo/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaReclamo(@PathVariable("id")int id) {
		Map<String, Object> salida = new HashMap<>();
		try {
			service.eliminaReclamo(id);
					salida.put("mensaje", "Se eliminó correctamente.");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error al eliminar el registro.");
		}
		return ResponseEntity.ok(salida);
	}
	
}
