package com.example.rh.servicios;

import com.example.rh.entidades.Empleado;
import java.util.List;

public interface EmpleadoServicio {

    public List<Empleado> listarEmpleado();

    public Empleado saveChange(Empleado empleado);

    public void delete(Empleado empleado);

    public Empleado find(Empleado empleado);

    public Empleado findById(Long idEmpleado);

}
