package com.example.rh.Dao;

import com.example.rh.entidades.Empleado;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoDao  extends CrudRepository<Empleado, Long>{
    
}
