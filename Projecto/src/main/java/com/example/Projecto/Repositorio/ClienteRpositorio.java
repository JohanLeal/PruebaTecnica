package com.example.Projecto.Repositorio;

import com.example.Projecto.Model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRpositorio extends JpaRepository<Clientes, Integer> {
}
