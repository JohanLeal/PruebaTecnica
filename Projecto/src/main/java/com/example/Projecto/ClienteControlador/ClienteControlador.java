package com.example.Projecto.ClienteControlador;

import com.example.Projecto.DTO.Request.ModificarClienteDtoRequest;
import com.example.Projecto.Model.Clientes;
import com.example.Projecto.DTO.Request.ClienteDtoRequest;
import com.example.Projecto.DTO.Response.ClienteDtoResponse;
import com.example.Projecto.Servicio.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/Clientes")
public class ClienteControlador {

    @Autowired
    ICliente iCliente;

    @GetMapping("/Listar")
    public List<ClienteDtoResponse> ObtenerClientes(){
        return iCliente.ObtenerClientes();
    }
    @PostMapping("/Crear")
    public Object GuardarCliente(@RequestBody ClienteDtoRequest clienteDtoRequest){
        return iCliente.GuardarCliente(clienteDtoRequest);
    }

    @PostMapping("/Modificar")
    public Object modificarClientes(int idCliente, @RequestBody ModificarClienteDtoRequest modificarClienteDtoRequest){
        return iCliente.modificarClientes(idCliente, modificarClienteDtoRequest);
    }

    @DeleteMapping( path = "/Borrar")
    public Object eliminarCliente(int idCliente){
        return iCliente.eliminarCliente(idCliente);
    }
}
