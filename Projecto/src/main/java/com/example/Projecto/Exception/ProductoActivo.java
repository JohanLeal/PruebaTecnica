package com.example.Projecto.Exception;

public class ProductoActivo extends RuntimeException {
    public ProductoActivo(String mensaje){
        super(mensaje);
    }
}
