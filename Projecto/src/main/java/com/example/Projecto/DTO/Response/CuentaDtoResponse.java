package com.example.Projecto.DTO.Response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CuentaDtoResponse {

    private int idCuentas;

    private String tipoCuenta;

    private String numeroCuenta;

    private String estadoCuenta;

    private int saldo;

    private String exentaGMF;

    private String fechaCreacionCuenta;

    private String fechaModificacionCuenta;
}
