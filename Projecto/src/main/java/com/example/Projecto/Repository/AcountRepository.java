package com.example.Projecto.Repository;

import com.example.Projecto.Model.Clientes;
import com.example.Projecto.Model.Cuentas;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface AcountRepository extends CrudRepository<Cuentas, Integer> {
    public ArrayList<Cuentas> findBynumeroCuenta(int numeroCuenta);
}
