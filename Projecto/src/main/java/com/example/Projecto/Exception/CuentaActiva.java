package com.example.Projecto.Exception;

public class CuentaActiva extends RuntimeException{
    public CuentaActiva(String mensaje){
        super(mensaje);
    }
}
