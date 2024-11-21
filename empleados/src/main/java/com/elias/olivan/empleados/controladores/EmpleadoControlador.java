package com.elias.olivan.empleados.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.elias.olivan.empleados.dao.EmpleadoDAO;
import com.elias.olivan.empleados.entidades.Empleado;

import java.util.List;

@RestController
@RequestMapping("/empleados")
@CrossOrigin(origins = {"http://localhost:5173", "http://192.168.237.93:5173"})
public class EmpleadoControlador {

    @Autowired
    private EmpleadoDAO empleadoDAO;

    // Crear nuevo empleado
    @PostMapping
    public ResponseEntity<String> crearEmpleado(@RequestBody Empleado empleado) {
        empleadoDAO.crearEmpleado(empleado);
        return new ResponseEntity<>("Empleado creado con éxito", HttpStatus.CREATED);
    }

    // Obtener empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Integer id) {
        Empleado empleado = empleadoDAO.obtenerEmpleadoPorId(id);
        return ResponseEntity.ok(empleado);
    }

    // Obtener todos los empleados
    @GetMapping
    public List<Empleado> obtenerTodosLosEmpleados() {
        return empleadoDAO.obtenerTodosLosEmpleados();
    }

    // Actualizar empleado
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado) {
        empleado.setId(id);
        empleadoDAO.actualizarEmpleado(empleado);
        return ResponseEntity.ok("Empleado actualizado con éxito");
    }

    // Eliminar empleado por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable Integer id) {
        empleadoDAO.eliminarEmpleadoPorId(id);
        return ResponseEntity.ok("Empleado eliminado con éxito");
    }
}