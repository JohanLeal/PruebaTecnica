package com.example.Projecto.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClientes;

    private String TipoIdentificacion;

    private int numeroidentificacion;

    private String Nombres;

    private String Apellidos;

    private String Correo;

    private String Fecha_Nacimiento;

    private String Fecha_Creacion;

    private String Fecha_Modificacion;

    @OneToMany(mappedBy = "clientes", cascade = CascadeType.ALL)
    private List<Cuentas> idCuentas;

}
