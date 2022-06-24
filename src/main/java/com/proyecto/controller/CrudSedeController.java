package com.proyecto.controller;


import com.proyecto.entidad.Sede;
import com.proyecto.service.SedeService;
import com.proyecto.util.AppSettings;


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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/url/crudSede")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class CrudSedeController {

    @Autowired
    private SedeService sedeService;

    @GetMapping("/listaSedexNombre/{filtro}")
    @ResponseBody
    public ResponseEntity<List<Sede>> consulta(@PathVariable("filtro")String filtro) {
        List<Sede> salida = null;
        try {
            if (filtro.equals("todos")) {
                salida = sedeService.listaSedexNombre("%");

            } else {
                salida = sedeService.listaSedexNombre("%" + filtro + "%");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(salida);
    }

    @PostMapping("/registrarSedeNuevo")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> registraSede(@RequestBody Sede obj) {

        Map<String, Object> salida = new HashMap<>();
        try {
            obj.setIdSede(0);
            Sede objSalida = sedeService.insertaActualizaSede(obj);
            if (objSalida == null) {
                salida.put("mensaje", "Error en el registro ");
            } else {
                salida.put("mensaje", "Registro exitoso con el Id: " + obj.getIdSede());
            }
        } catch (Exception e) {
            salida.put("mensaje", "Error en el registro");
            e.printStackTrace();
        }
        return ResponseEntity.ok(salida);
    }

    @PutMapping("/actualizaSede")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> actualizaSede(@RequestBody Sede obj) {
        Map<String, Object> salida = new HashMap<>();
        try {
            Sede objSalida = sedeService.insertaActualizaSede(obj);
            if (objSalida == null) {
                salida.put("mensaje", "Error al actualizar ");
            } else {
                salida.put("mensaje", "Actualizacion exitoso con el Id: " + obj.getIdSede());
            }
        } catch (Exception e) {
            salida.put("mensaje", "Error al actualizar ");
            e.printStackTrace();
        }
        return ResponseEntity.ok(salida);
    }

    @DeleteMapping("/eliminaSede/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> elimina(@PathVariable("id")int id) {
        Map<String, Object> salida = new HashMap<>();
        try {
            Optional<Sede> opt = sedeService.buscaSede(id);
            if (opt.isPresent()) {
                sedeService.eliminaSede(id);
                Optional<Sede> optSalida = sedeService.buscaSede(id);
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
