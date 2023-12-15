package com.example.rh.servicios;

import com.example.rh.Dao.EmpleadoDao;
import com.example.rh.entidades.Empleado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EmpleadoServicioImp implements EmpleadoServicio{
    
    @Autowired
    EmpleadoDao empleadoDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Empleado> listarEmpleado(){
        return (List<Empleado>) empleadoDao.findAll();
    };

    @Override
    @Transactional
    public Empleado saveChange(Empleado empleado){
        return empleadoDao.save(empleado);
    };

    @Override
    @Transactional
    public void delete(Empleado empleado){
        empleadoDao.delete(empleado);
    };

    @Override
    @Transactional(readOnly = true)
    public Empleado find(Empleado empleado){
        return empleadoDao.findById(empleado.getIdEmpleado()).orElse(null);
    };

    @Override
    @Transactional(readOnly = true)
    public Empleado findById(Long idEmpleado){
        return empleadoDao.findById(idEmpleado).orElse(null);
    };
    
    
    
}
