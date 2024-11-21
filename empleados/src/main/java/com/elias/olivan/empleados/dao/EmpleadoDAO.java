package com.elias.olivan.empleados.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.elias.olivan.empleados.entidades.Empleado;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmpleadoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Crear empleado
    public int crearEmpleado(Empleado empleado) {
        String sql = "INSERT INTO empleados (nombre, apellido, posicion) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, empleado.getNombre(), empleado.getApellido(), empleado.getPosicion());
    }

    // Leer empleado por ID
    public Empleado obtenerEmpleadoPorId(Integer id) {
        String sql = "SELECT * FROM empleados WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new EmpleadoRowMapper(), id);
    }

    // Leer todos los empleados
    public List<Empleado> obtenerTodosLosEmpleados() {
        String sql = "SELECT * FROM empleados";
        return jdbcTemplate.query(sql, new EmpleadoRowMapper());
    }

    // Actualizar empleado
    public int actualizarEmpleado(Empleado empleado) {
        String sql = "UPDATE empleados SET nombre = ?, apellido = ?, posicion = ? WHERE id = ?";
        return jdbcTemplate.update(sql, empleado.getNombre(), empleado.getApellido(), empleado.getPosicion(), empleado.getId());
    }

    // Eliminar empleado por ID
    public int eliminarEmpleadoPorId(Integer id) {
        String sql = "DELETE FROM empleados WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // RowMapper para mapear los resultados de la consulta a un objeto Empleado
    private static class EmpleadoRowMapper implements RowMapper<Empleado> {
        @Override
        public Empleado mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Empleado(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("posicion")
            );
        }
    }
}