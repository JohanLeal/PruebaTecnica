package com.example.Projecto.Exception;

public class NoTieneDinero extends RuntimeException{
    public NoTieneDinero(String mensaje){
        super(mensaje);
    }
}
