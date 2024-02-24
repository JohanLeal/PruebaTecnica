package com.example.Projecto.Controller;

import com.example.Projecto.Exception.MenorDeEdadException;
import com.example.Projecto.Model.Clientes;
import com.example.Projecto.Model.Cuentas;
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
    @PostMapping("/guardarCliente")
    public Clientes GuardarCliente(@RequestBody Clientes client){
        return this.clientServi.GuardarCliente(client);
    }

    @PostMapping("/Clientes/modificarCliente")
    public Clientes modificarClientes(@RequestBody Clientes clientes){
        return this.clientServi.modificarClientes(clientes);
    }



    @GetMapping(path = "/{numeroidentificacion}")
    public ArrayList<Clientes> obtenerPornd(@PathVariable("numeroidentificacion")int numeroidentificacion){
        return this.clientServi.obtenerPornd(numeroidentificacion);
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
