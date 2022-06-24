package com.proyecto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

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
import com.proyecto.service.ClienteService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/cliente")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ClienteController {

	
	
	// rama pc3 hola mundo
	
	
	
	
	
	
	//  22 04 1802
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	@ResponseBody
	public List<Cliente> listaCliente() {
		return clienteService.listaCliente();
	}
	
  ///registrar cliente //////
	
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertaCliente(@RequestBody Cliente obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			obj.setEstado(1);
			obj.setFechaRegistro(new Date());
			List<Cliente> lista = clienteService.listaClientePorDni(obj.getDni());
			
			if (CollectionUtils.isEmpty(lista)) {
				Cliente objSalida = clienteService.InsertaActualizaCliente(obj);
				if (objSalida == null) {
					salida.put("mensaje", "Error en el registro " );
				}else {
					salida.put("mensaje", "registro exitoso " );
				}	
			}else {
				 salida.put("mensaje", "El dni ya existe : " + obj.getDni());
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en el registro " + e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}
	
	//cambio 5
	//cambio 22 05
	///consulta cliente /////
	
	
	@GetMapping("/listaClienteConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaClienteNombreDniUbigeo(
			@RequestParam(name = "nombres", required = false, defaultValue = "") String nombres,
			@RequestParam(name = "apellidos", required = false, defaultValue = "") String apellidos,
			@RequestParam(name = "dni", required = false, defaultValue = "") String dni,
			@RequestParam(name = "idUbigeo", required = false, defaultValue = "-1") int idUbigeo,
			@RequestParam(name = "estado", required = true, defaultValue = "1") int estado) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Cliente> lista = clienteService.listaClientePorNombreDniUbigeo("%"+nombres+"%", "%"+apellidos+"%" , dni, idUbigeo, estado);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "fgdfgdsgh");
		}
		return ResponseEntity.ok(salida);
		
	}
	
	
/////////////crud cliente////////
	
	
	
	/////////////crud 1 get/////////
	
	@GetMapping("/listaClientePorNombreLike/{nom}")
	@ResponseBody
	public ResponseEntity<List<Cliente>> listaClientePorNombreLike(@PathVariable("nom") String nom) {
		List<Cliente> lista  = null;
		try {
			if (nom.equals("todos")) {
				lista = clienteService.listaClientePorNombreLike("%");
			}else {
				lista = clienteService.listaClientePorNombreLike("%" + nom + "%");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	
	
	
	////////////crud 2 post/////////
	
	@PostMapping("/registraCliente")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaCliente2(@RequestBody Cliente obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdCliente(0);
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Cliente objSalida =  clienteService.insertaActualizaCliente(obj);
			if (objSalida == null) {
				salida.put("mensaje","Error en el registro ");
			} else {
				salida.put("mensaje","registro exitoso " );
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje","Error al registrar " + e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
	///////////crud 3 put//////////
	
	
	@PutMapping("/actualizaCliente")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> actualizaCliente(@RequestBody Cliente obj){
		HashMap<String, Object> salida = new HashMap<>();
		try {
				Cliente objSalida = clienteService.InsertaActualizaCliente(obj);
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
	
	
	
////////////crud  eliminar3455/////////
	
	
	

	/////////crud 6655 eliminar ////////////
	
	
	@DeleteMapping("/eliminaCliente/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaDocente(@PathVariable("id")int id) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Cliente opt = clienteService.buscaClientePorId(id);
			if (opt != null) {
				clienteService.eliminaCliente(id);
				Cliente optDocente = clienteService.buscaClientePorId(id);
				if (optDocente == null) {
					salida.put("mensaje", "Se eliminó correctamente.");
				} else {
					salida.put("mensaje", "No se eliminó, ya que el registro esta relacionado.");
				}
			}else {
				salida.put("mensaje", "No existe el ID que se desea eliminar.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se eliminó, ya que el registro esta relacionado.");
		}
		return ResponseEntity.ok(salida);
	}
	
	//////////////////45
	
	
	
	
}
