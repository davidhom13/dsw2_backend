package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.proyecto.entidad.Sede;
import com.proyecto.service.SedeService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/sede")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class SedeController {

    @Autowired
    private SedeService service;

    @ResponseBody
    @GetMapping
    public ResponseEntity<List<Sede>> listaSede(){
        List<Sede> lista = service.listaSede();
        return ResponseEntity.ok(lista);

    }

    @ResponseBody
    @PostMapping
    public ResponseEntity<Map<String,Object>>registrarSede(@RequestBody Sede obj){
        Map<String, Object> salida = new HashMap<>();

        try {
            obj.setFechaRegistro(new Date());
            obj.setEstado(1);

            Sede objSalida = service.RegistrarSede(obj);{

                if (objSalida == null) {
                    salida.put("mensaje", "Error en el registro ");
                }else {
                    salida.put("mensaje", "Registro exitoso con el ID:  " + obj.getIdSede());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            salida.put("mensaje", "Error en el registro " + e.getMessage());
        }
        return ResponseEntity.ok(salida);
    }

    @GetMapping("/listaSedeConParametros")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listaSedexNombrePaisEstado(
            @RequestParam(name = "nombre", required = false, defaultValue = "") String nombre,
            @RequestParam(name = "codigoPostal", required = false, defaultValue = "") String codigoPostal,
            @RequestParam(name = "idPais", required = false, defaultValue = "-1") int idPais,
            @RequestParam(name = "estado", required = true, defaultValue = "1") int estado) {
        Map<String, Object> salida = new HashMap<>();
        try {
            List<Sede> lista = service.listaSedexNombrePaisEstado("%"+nombre+"%", codigoPostal, idPais, estado);
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

}
