package com.example.Projecto.Repositorio;

import com.example.Projecto.Model.Cuentas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepositorio extends JpaRepository<Cuentas, Integer> {


    public Cuentas findByNumeroCuenta(String numeroCuenta);
}
