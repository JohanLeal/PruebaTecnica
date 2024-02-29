package com.example.Projecto.Servicio.Impl;

import com.example.Projecto.DTO.Request.ModificarClienteDtoRequest;
import com.example.Projecto.Model.Clientes;
import com.example.Projecto.Model.Cuentas;
import com.example.Projecto.DTO.Request.ClienteDtoRequest;
import com.example.Projecto.DTO.Response.ClienteDtoResponse;
import com.example.Projecto.Repositorio.CuentaRepositorio;
import com.example.Projecto.Repositorio.ClienteRepositorio;
import com.example.Projecto.Servicio.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
public class ClienteServicio implements ICliente {

    @Autowired
    ClienteRepositorio clienteRepositorio;

    @Autowired
    CuentaRepositorio cuentaRepositorio;

    //Creacion de metodo para listar clientes
    public List<ClienteDtoResponse> ObtenerClientes(){
        List<Clientes> clientes = clienteRepositorio.findAll();
        List<ClienteDtoResponse> clientesDto = new ArrayList<>();
        //Se hace un ciclo para guardar en el objeto "cliente" toda la informacion que se trajo en la lista "Clientes"
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

            //Se agrega el objeto "cliente" y se repite el ciclo hasta que no haya mas objetos en la lista "clientes"
            clientesDto.add(clienteDtoResponse);
        }
        return clientesDto;
    }


    //Se crea el metodo para guarda clientes por medio de los parametro pedidos por el "ClienteDtoRequest"
    public Object GuardarCliente(ClienteDtoRequest clienteDtoRequest){

        //Se consulta y guarda la "Fecha de nacimiento" como la fecha actual del programa
        LocalDate fechaNacimiento = LocalDate.parse(clienteDtoRequest.getFecha_Nacimiento());
        LocalDate fechaActual = LocalDate.now();

        //Se crea un objeto tipo "Clientes"
        Clientes cliente = new Clientes();

        //Se dispone a aplicar la informacion que es traida por el "DTO" como la que se colocaria por defecto en una cuenta
        cliente.setTipoIdentificacion("Cedula");
        cliente.setIdClientes(clienteDtoRequest.getIdClientes());
        cliente.setNumeroidentificacion(clienteDtoRequest.getNumeroidentificacion());
        cliente.setApellidos(clienteDtoRequest.getApellidos());
        cliente.setNombres(clienteDtoRequest.getNombres());
        cliente.setCorreo(clienteDtoRequest.getCorreo());
        cliente.setFecha_Nacimiento(clienteDtoRequest.getFecha_Nacimiento());
        cliente.setFecha_Creacion(String.valueOf(fechaActual));
        cliente.setFecha_Modificacion(String.valueOf(fechaActual));

        //Antes de guardar el cliente, se hace la verificacion de si es mayor de edad o no
        Period periodo = Period.between(fechaNacimiento, fechaActual);
        int edad = periodo.getYears();
        if(edad<18){
            return "El cliente es menor de edad";
        }else{
            //Se guarda el cliente verificando es mayor de edad
            return clienteRepositorio.save(cliente) + "Se creo el cliente";
        }
    }

    //Se crea el metodo para modificar un cliente
    public Object modificarClientes(int idCliente, ModificarClienteDtoRequest modificarClienteDtoRequest){
        //En esta parte se establecen los datos de auditoria
        LocalDate fechaActual = LocalDate.now();
        modificarClienteDtoRequest.setFecha_Modificacion(String.valueOf(fechaActual));
        //Se guarda el cliente de acurdo a los datos otorgados por los DTOs
        clienteRepositorio.save(setearClientes(idCliente, modificarClienteDtoRequest));
        return "Se modifico el cliente";
    }

    //Metodo para eliminar clientes por medio del idClientes
    public Object eliminarCliente(int idClientes){
        //Se busca y se guarda la informacion del cliente por medio del idClientes
        Clientes clientes = clienteRepositorio.findById(idClientes).orElse(null);
        //Se busca y guarda las cuentas asociadas al cliente para hacer una validacion mas adelante
        List<Cuentas> cuentas = clientes.getIdCuentas();
        //Se hace la validacion de: Si no tiene cuentas asociadas se elimine la cuenta
        //y si tiene alguna cuenta asociada no la elimine
        if (cuentas.isEmpty()){
            clienteRepositorio.deleteById(idClientes);
        } else{
            return "El cliente tiene cuentas activas";
        }
        return "Se borro la cuenta";
    }

    //Este metodo se utiliza para guardar la informacion que se trae del DTO guardarla en el objeto Cuentas
    public Clientes setearClientes(int idCliente, ModificarClienteDtoRequest modificarClienteDtoRequest){
        Clientes clientes = clienteRepositorio.findById(idCliente).orElse(null);

        clientes.setNumeroidentificacion(modificarClienteDtoRequest.getNumeroidentificacion());
        clientes.setNombres(modificarClienteDtoRequest.getNombres());
        clientes.setApellidos(modificarClienteDtoRequest.getApellidos());
        clientes.setCorreo(modificarClienteDtoRequest.getCorreo());
        clientes.setFecha_Nacimiento(modificarClienteDtoRequest.getFecha_Nacimiento());
        clientes.setTipoIdentificacion(modificarClienteDtoRequest.getTipoIdentificacion());
        return clientes;
    }
}
