package com.example.Projecto.Repository;

import com.example.Projecto.Model.Clientes;
import com.example.Projecto.Model.Cuentas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Clientes, Integer> {
    public ArrayList<Clientes> findBynumeroidentificacion(int numeroidentificacion);
}
