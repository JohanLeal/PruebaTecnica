package com.example.Projecto.Service;

import com.example.Projecto.Exception.MenorDeEdadException;
import com.example.Projecto.Exception.ProductoActivo;
import com.example.Projecto.Model.Clientes;
import com.example.Projecto.Model.Cuentas;
import com.example.Projecto.Repository.AcountRepository;
import com.example.Projecto.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AcountRepository acountRepository;


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

    public void eliminarCliente(int idCliente){
        ArrayList<Cuentas>cuentas = acountRepository.findByIdClientes(idCliente);
        if(cuentas.size()==1){
            String array = cuentas.toString();
            int cuentaId;
            cuentaId = Integer.parseInt(String.valueOf(array.substring(20,21)));
            if (idCliente == cuentaId){
                throw new ProductoActivo("No se puede borrar la cuenta, el cliente tiene un producto activo");
            }
        }else if (cuentas.size()>0){
                String array = cuentas.toString();
                int cuentaId;
                cuentaId = Integer.parseInt(String.valueOf(array.substring(20,22)));
                if (idCliente == cuentaId){
                throw new ProductoActivo("No se puede borrar la cuenta, el cliente tiene un producto activo");
                }
        }
        else{
            try{
                clientRepository.deleteById(idCliente);
            }catch(Exception err){
                System.out.println(err);
            }
        }
    }
}
