package com.example.Projecto.Servicio;

import com.example.Projecto.DTO.Request.ClienteDtoRequest;
import com.example.Projecto.DTO.Request.ModificarClienteDtoRequest;
import com.example.Projecto.DTO.Response.ClienteDtoResponse;
import com.example.Projecto.Model.Clientes;

import java.util.List;

public interface ICliente {

    List<ClienteDtoResponse> ObtenerClientes();

    Object GuardarCliente(ClienteDtoRequest clienteDtoRequest);

    Object modificarClientes(int idCliente, ModificarClienteDtoRequest modificarClienteDtoRequest);

    Object eliminarCliente(int idClientes);

    Object setearClientes(int idCliente, ModificarClienteDtoRequest modificarClienteDtoRequest);
}
