import { useState } from 'react'
import './App.css'
import EmpleadoCrud from './components/EmpleadosCrud'

function App() {

  return (
    <div className="App">
      <h1>Aplicación CRUD de Empleados</h1>
      <EmpleadoCrud />
    </div>

  )
}

export default App
