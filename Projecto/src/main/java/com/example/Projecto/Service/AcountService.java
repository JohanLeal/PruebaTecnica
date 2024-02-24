package com.example.Projecto.Service;

import com.example.Projecto.Exception.MenorDeEdadException;
import com.example.Projecto.Model.Clientes;
import com.example.Projecto.Model.Cuentas;
import com.example.Projecto.Repository.AcountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

@Service
public class AcountService {

    @Autowired
    AcountRepository acountRepository;

    public Cuentas guardarCuenta(Cuentas cuentas){
        LocalDate fechaActual = LocalDate.now();
        cuentas.setFechaCreacionCuenta(String.valueOf(fechaActual));
        cuentas.setFechaModificacionCuenta(String.valueOf(fechaActual));
        return acountRepository.save(cuentas);
    }

    public Cuentas modificarCuentas(Cuentas cuentas){{
        LocalDate fechaActual = LocalDate.now();
        cuentas.setFechaModificacionCuenta(String.valueOf(fechaActual));
    }
        return acountRepository.save(cuentas);
    }

    public ArrayList<Cuentas> obtenerCuentas(){
        return (ArrayList<Cuentas>) acountRepository.findAll();
    }

}
