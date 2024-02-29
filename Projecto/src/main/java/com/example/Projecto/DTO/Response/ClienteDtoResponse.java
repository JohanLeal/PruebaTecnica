package com.example.Projecto.DTO.Response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteDtoResponse {

    private Integer idClientes;

    private String TipoIdentificacion;

    private int numeroidentificacion;

    private String Nombres;

    private String Apellidos;

    private String Correo;

    private String Fecha_Nacimiento;

    private String Fecha_Creacion;

    private String Fecha_Modificacion;

    private List<CuentaDtoResponse> idCuentas;
}
