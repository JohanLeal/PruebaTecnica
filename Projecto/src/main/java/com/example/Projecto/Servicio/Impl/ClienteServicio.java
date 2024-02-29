package com.example.Projecto.Servicio.Impl;

import com.example.Projecto.DTO.Request.ModificarClienteDtoRequest;
import com.example.Projecto.Model.Clientes;
import com.example.Projecto.Model.Cuentas;
import com.example.Projecto.DTO.Request.ClienteDtoRequest;
import com.example.Projecto.DTO.Response.ClienteDtoResponse;
import com.example.Projecto.Repositorio.CuentaRepositorio;
import com.example.Projecto.Repositorio.ClienteRpositorio;
import com.example.Projecto.Servicio.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
public class ClienteServicio implements ICliente {

    @Autowired
    ClienteRpositorio clientRepository;

    @Autowired
    CuentaRepositorio acountRepository;


    public List<ClienteDtoResponse> ObtenerClientes(){
        List<Clientes> clientes = clientRepository.findAll();
        List<ClienteDtoResponse> clientesDto = new ArrayList<>();
        for (Clientes cliente : clientes){
            ClienteDtoResponse clienteDtoResponse = new ClienteDtoResponse();
            clienteDtoResponse.setIdClientes(cliente.getIdClientes());
            clienteDtoResponse.setNumeroidentificacion(cliente.getNumeroidentificacion());
            clienteDtoResponse.setApellidos(cliente.getApellidos());
            clienteDtoResponse.setCorreo(cliente.getCorreo());
            clienteDtoResponse.setFecha_Creacion(cliente.getFecha_Creacion());
            clienteDtoResponse.setFecha_Modificacion(cliente.getFecha_Modificacion());
            clienteDtoResponse.setFecha_Nacimiento(cliente.getFecha_Nacimiento());
            clienteDtoResponse.setNombres(cliente.getNombres());
            clienteDtoResponse.setTipoIdentificacion(cliente.getTipoIdentificacion());

            clientesDto.add(clienteDtoResponse);
        }
        return clientesDto;
    }


    public Object GuardarCliente(ClienteDtoRequest clienteDtoRequest){

        LocalDate fechaNacimiento = LocalDate.parse(clienteDtoRequest.getFecha_Nacimiento());
        LocalDate fechaActual = LocalDate.now();

        Clientes cliente = new Clientes();

        cliente.setTipoIdentificacion("Cedula");
        cliente.setIdClientes(clienteDtoRequest.getIdClientes());
        cliente.setNumeroidentificacion(clienteDtoRequest.getNumeroidentificacion());
        cliente.setApellidos(clienteDtoRequest.getApellidos());
        cliente.setNombres(clienteDtoRequest.getNombres());
        cliente.setCorreo(clienteDtoRequest.getCorreo());
        cliente.setFecha_Nacimiento(clienteDtoRequest.getFecha_Nacimiento());
        cliente.setFecha_Creacion(String.valueOf(fechaActual));
        cliente.setFecha_Modificacion(String.valueOf(fechaActual));


        Period periodo = Period.between(fechaNacimiento, fechaActual);
        int edad = periodo.getYears();
        if(edad<18){
            return "El cliente es menor de edad";
        }else{
            return clientRepository.save(cliente) + "Se creo el cliente";
        }
    }

    public Object modificarClientes(int idCliente, ModificarClienteDtoRequest modificarClienteDtoRequest){
        LocalDate fechaActual = LocalDate.now();
        modificarClienteDtoRequest.setFecha_Modificacion(String.valueOf(fechaActual));
        clientRepository.save(setearClientes(idCliente, modificarClienteDtoRequest));
        return "Se modifico el cliente";
    }

    public Object eliminarCliente(int idClientes){
        Clientes clientes = clientRepository.findById(idClientes).orElse(null);
        List<Cuentas> cuentas = clientes.getIdCuentas();
        if (cuentas.isEmpty()){
            clientRepository.deleteById(idClientes);
        } else{
            return "El cliente tiene cuentas activas";
        }
        return "Se borro la cuenta";
    }

    public Clientes setearClientes(int idCliente, ModificarClienteDtoRequest modificarClienteDtoRequest){
        Clientes clientes = clientRepository.findById(idCliente).orElse(null);

        clientes.setNumeroidentificacion(modificarClienteDtoRequest.getNumeroidentificacion());
        clientes.setNombres(modificarClienteDtoRequest.getNombres());
        clientes.setApellidos(modificarClienteDtoRequest.getApellidos());
        clientes.setCorreo(modificarClienteDtoRequest.getCorreo());
        clientes.setFecha_Nacimiento(modificarClienteDtoRequest.getFecha_Nacimiento());
        clientes.setTipoIdentificacion(modificarClienteDtoRequest.getTipoIdentificacion());
        return clientes;
    }
}
