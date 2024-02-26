package com.example.Projecto.Service;

import com.example.Projecto.Exception.CuentaActiva;
import com.example.Projecto.Exception.NoTieneDinero;
import com.example.Projecto.Model.Cuentas;
import com.example.Projecto.Repository.AcountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

@Service
public class AcountService {

    @Autowired
    AcountRepository acountRepository;

    public Cuentas guardarCuenta(Cuentas cuentas){
        Random random = new Random();
        LocalDate fechaActual = LocalDate.now();
        String tipoCuenta = cuentas.getTipoCuenta();
        if(tipoCuenta.equalsIgnoreCase("Ahorros")){
            Long numeroCuenta = random.nextLong(99999999-10000000)+10000000;
            cuentas.setNumeroCuenta(Long.valueOf("" + 53 + numeroCuenta));
        }else if(tipoCuenta.equalsIgnoreCase("Corriente")){
            Long numeroCuenta = random.nextLong(99999999-10000000)+10000000;
            cuentas.setNumeroCuenta(Long.valueOf("" + 33 + numeroCuenta));
        }
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

    public ArrayList<Cuentas> obtenerPorId(int idClientes){
        return acountRepository.findByIdClientes(idClientes);
    }

    public Cuentas consignarDinero(int dinero, Cuentas cuentas){
        cuentas.setSaldo(cuentas.getSaldo()+dinero);
        return acountRepository.save(cuentas);
    }

    public Cuentas retirarDinero(int dinero, Cuentas cuentas){
        if ((cuentas.getSaldo()-dinero)<0){
            throw new NoTieneDinero("Dinero insuficiente en la cuenta");
        }else {
            cuentas.setSaldo(cuentas.getSaldo()-dinero);
            return acountRepository.save(cuentas);
        }
    }

    public void transferirDinero(int dinero, Cuentas cuenta1, Cuentas cuenta2){
        if((cuenta1.getSaldo()-dinero)<0){
            throw new NoTieneDinero("Dinero insuficiente para hacer la accion");
        }else {
            cuenta1.setSaldo(cuenta1.getSaldo() - dinero);
            cuenta2.setSaldo(cuenta2.getSaldo() + dinero);
        }
        acountRepository.save(cuenta1);
        acountRepository.save(cuenta2);
    }

    public void borrarCuenta(int idCliente, Cuentas cuentas){
        int saldo = cuentas.getSaldo();
        if(saldo==0){
            acountRepository.deleteById(idCliente);
        }else{
            throw new CuentaActiva("Esta persona tiene dinero en su cuenta!!");
        }
    }

}
