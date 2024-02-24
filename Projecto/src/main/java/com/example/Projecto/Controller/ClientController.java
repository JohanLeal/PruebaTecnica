package com.example.Projecto.Controller;

import com.example.Projecto.Exception.MenorDeEdadException;
import com.example.Projecto.Model.Clientes;
import com.example.Projecto.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/Clientes")
public class ClientController {

    @Autowired
    ClientService clientServi;

    @GetMapping()
    public ArrayList<Clientes> ObtenerClientes(){
        return clientServi.ObtenerClientes();
    }
/*
    @GetMapping("/{idClientes}")
    public Date getDateForIden(@PathVariable("idClientes")int idClientes){
        return this.clientServi.getDateForIden(idClientes);
    }
*/
    @PostMapping("/guardarCliente")
    public Clientes GuardarCliente(@RequestBody Clientes client){
        LocalDate fechaNacimiento = LocalDate.parse(client.getFecha_Nacimiento());
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(fechaNacimiento, fechaActual);
        int edad = periodo.getYears();
        if(edad<18){
            throw new MenorDeEdadException("Cliente menor de edad, no se puede añadir");
        }else {
            System.out.println("Registro exitoso");
        }
        return this.clientServi.GuardarCliente(client);
    }

    @GetMapping(path = "/{numeroidentificacion}")
    public ArrayList<Clientes> obtenerPornd(@PathVariable("numeroidentificacion")int numeroidentificacion){
        return this.clientServi.obtenerPornd(numeroidentificacion);
    }

    @GetMapping(path = "/Cuentas/{numeroCuenta}")
    public int listarClientes(@PathVariable("numeroCuenta")int numeroCuenta){
        return this.clientServi.listarCuentas(numeroCuenta);
    }

    @DeleteMapping( path = "/Borrar/{idCliente}")
    public String eliminarCliente(@PathVariable("idCliente")int idCliente){
        boolean ok = this.clientServi.eliminarCliente(idCliente);
        if(ok){
            return "Se elimino el usuario con numero de documento: " + idCliente;
        }else{
            return "No se pudo eliminar el usuario con numero de documento: " + idCliente;
        }
    }


}
