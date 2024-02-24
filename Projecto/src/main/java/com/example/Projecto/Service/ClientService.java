package com.example.Projecto.Service;

import com.example.Projecto.Model.Clientes;
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
        return clientRepository.save(cliente);
    }

    public int listarCuentas(int numeroCuenta){
        ArrayList<Clientes> clientes = (clientRepository.findByNumeroCuenta(numeroCuenta));
        return clientes.size();
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

    /*public Date getDateForIden(int idClientes){
        Optional<Clientes>clientes = clientRepository.findByIdClientes(idClientes);

        if(clientes.isPresent()){
            Clientes cliente =clientes.get();
            String fechaNacimientoString = cliente.getFechaNacimiento();

            SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd");
                try{
                    Date fechaNacimiento = std.parse(fechaNacimientoString);
                    return fechaNacimiento;
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
        }else{
            return null;
        }
    }
*/
}
