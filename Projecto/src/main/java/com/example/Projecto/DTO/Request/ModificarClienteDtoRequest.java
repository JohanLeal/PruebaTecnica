package com.example.Projecto.DTO.Request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ModificarClienteDtoRequest {

    private String TipoIdentificacion;

    private int numeroidentificacion;

    private String Nombres;

    private String Apellidos;

    private String Correo;

    private String Fecha_Nacimiento;

    private String Fecha_Modificacion;
}
