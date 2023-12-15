package com.example.rh.web;

import com.example.rh.entidades.Empleado;
import com.example.rh.exepcion.RecursoNoEncontradoExepcion;
import com.example.rh.servicios.EmpleadoServicio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rh-app")
@CrossOrigin(origins = "http://localhost:3000")
public class EmpleadoControlador {

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    private EmpleadoServicio empleadoServicio;

    // http://localhost:8080/rh-app/empleados
    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados() {
        var empleados = empleadoServicio.listarEmpleado();
        empleados.forEach(empleado -> logger.info(empleado.toString()));
        return empleados;
    }

    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado) {
        logger.info("Empleado a Agregar: " + empleado);
        return empleadoServicio.saveChange(empleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Long id) {
        Empleado empleado = empleadoServicio.findById(id);
        if (empleado == null) {
            throw new RecursoNoEncontradoExepcion("No se encontro el ID: " + id);
        }
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id,
            @RequestBody Empleado empleadoRecibido) {
        Empleado empleado = empleadoServicio.findById(id);
        if (empleado == null) {
            throw new RecursoNoEncontradoExepcion("No se encontro el ID: " + id);
        }
        empleado.setNombre(empleadoRecibido.getNombre());
        empleado.setDepartamento(empleadoRecibido.getDepartamento());
        empleado.setSueldo(empleadoRecibido.getSueldo());
        empleadoServicio.saveChange(empleado);
        return ResponseEntity.ok(empleado);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>>
            eliminarEmpleado(@PathVariable Long id) {
        Empleado empleado = empleadoServicio.findById(id);
        if (empleado == null) {
            throw new RecursoNoEncontradoExepcion("No se encontro el ID: " + id);
        }
        empleadoServicio.delete(empleado);
        //Json {"eliminado", true}
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
