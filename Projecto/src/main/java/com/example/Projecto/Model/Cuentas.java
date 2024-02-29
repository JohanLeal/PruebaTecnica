package com.example.Projecto.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;


@Entity
@Table(name = "cuentas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cuentas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCuentas;

    private String tipoCuenta;

    private String numeroCuenta;

    private String estadoCuenta;

    private int saldo;

    private String exentaGMF;

    private String fechaCreacionCuenta;

    private String fechaModificacionCuenta;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Clientes clientes;

}
