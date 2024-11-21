import React, { useState, useEffect } from 'react';
import axios from 'axios';

const EmpleadoCrud = () => {
  const [empleados, setEmpleados] = useState([]);
  const [empleado, setEmpleado] = useState({ id: '', nombre: '', apellido: '', posicion: '' });
  const [editando, setEditando] = useState(false);

  // Obtener todos los empleados al cargar el componente
  useEffect(() => {
    axios.get('http://localhost:8080/empleados')
      .then((response) => {
        console.log('Datos obtenidos de la API:', response.data); // Verifica la respuesta
        setEmpleados(response.data);
      })
      .catch((error) => {
        console.error('Hubo un error al obtener los empleados:', error);
      });
  }, []);



  // Crear o actualizar empleado
  const handleSubmit = (e) => {
    e.preventDefault();
    if (!empleado.nombre || !empleado.apellido || !empleado.posicion) {
      alert('Todos los campos son requeridos');
      return;
    }

    if (editando) {
      // Actualizar empleado
      axios.put(`http://localhost:8080/empleados/${empleado.id}`, empleado)
        .then(() => {
          setEmpleados(prevEmpleados =>
            prevEmpleados.map(emp => (emp.id === empleado.id ? empleado : emp))
          );
          setEmpleado({ id: '', nombre: '', apellido: '', posicion: '' });
          setEditando(false);
        })
        .catch((error) => {
          console.error('Error al actualizar el empleado:', error);
        });
    } else {
      // Crear nuevo empleado
      axios.post('http://localhost:8080/empleados', empleado)
        .then((response) => {
          setEmpleados(prevEmpleados => [...prevEmpleados, response.data]);
          setEmpleado({ id: '', nombre: '', apellido: '', posicion: '' });
        })
        .catch((error) => {
          console.error('Error al crear el empleado:', error);
        });
    }
  };

  // Eliminar empleado
  const handleDelete = (id) => {
    axios.delete(`http://localhost:8080/empleados/${id}`)
      .then(() => {
        setEmpleados(empleados.filter(emp => emp.id !== id));
      })
      .catch((error) => {
        console.error('Error al eliminar el empleado:', error);
      });
  };

  // Editar empleado
  const handleEdit = (emp) => {
    setEmpleado(emp);
    setEditando(true);
  };

  return (
    <div>
      <h1>CRUD Empleados</h1>

      {/* Formulario de creación/actualización */}
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Nombre"
          value={empleado.nombre}
          onChange={(e) => setEmpleado({ ...empleado, nombre: e.target.value })}
        />
        <input
          type="text"
          placeholder="Apellido"
          value={empleado.apellido}
          onChange={(e) => setEmpleado({ ...empleado, apellido: e.target.value })}
        />
        <input
          type="text"
          placeholder="Posición"
          value={empleado.posicion}
          onChange={(e) => setEmpleado({ ...empleado, posicion: e.target.value })}
        />
        <button type="submit">{editando ? 'Actualizar' : 'Crear'} Empleado</button>
      </form>

      {/* Lista de empleados */}
      {empleados.length === 0 ? (
        <p>Cargando empleados...</p>
      ) : (
        <ul>
          {empleados.map((emp, index) => (
            <li key={emp.id || index}> {/* Si `emp.id` no es único, usamos el índice */}
              <p>{emp.nombre} {emp.apellido} - {emp.posicion}</p>
              <button onClick={() => handleEdit(emp)}>Editar</button>
              <button onClick={() => handleDelete(emp.id)}>Eliminar</button>
            </li>
          ))}
        </ul>


      )}
    </div>
  );

};

export default EmpleadoCrud;
