package com.example.Projecto.Service;

import com.example.Projecto.Exception.MenorDeEdadException;
import com.example.Projecto.Exception.ProductoActivo;
import com.example.Projecto.Model.Clientes;
import com.example.Projecto.Model.Cuentas;
import com.example.Projecto.Repository.AcountRepository;
import com.example.Projecto.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;


    public ArrayList<Clientes>ObtenerClientes(){
        return (ArrayList<Clientes>) clientRepository.findAll();
    }

    public Clientes GuardarCliente(Clientes cliente){
        LocalDate fechaNacimiento = LocalDate.parse(cliente.getFecha_Nacimiento());
        LocalDate fechaActual = LocalDate.now();
        cliente.setFecha_Creacion(String.valueOf(fechaActual));
        cliente.setFecha_Modificacion(String.valueOf(fechaActual));
        Period periodo = Period.between(fechaNacimiento, fechaActual);
        int edad = periodo.getYears();
        if(edad<18){
            throw new MenorDeEdadException("Cliente menor de edad, no se puede añadir");
        }
        return clientRepository.save(cliente);
    }

    public Clientes modificarClientes(Clientes clientes){
        LocalDate fechaActual = LocalDate.now();
        clientes.setFecha_Modificacion(String.valueOf(fechaActual));
        return clientRepository.save(clientes);
    }

    public ArrayList<Clientes> obtenerPornd(int numeroidentificacion){
        return clientRepository.findBynumeroidentificacion(numeroidentificacion);
    }

    public boolean eliminarCliente(int idCliente){
        try{
            clientRepository.deleteById(idCliente);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}
