package Controlador;

import Modelo.modelo;
import Vista.CrearCuenta;
import Vista.Index;

import java.sql.*;

public class controlador {
    
    private java.sql.Connection getConnection() throws SQLException{
        return modelo.getInstance();
    }
    
    private Index view;
    private modelo model;

    private CrearCuenta regis;

    public controlador(Index view, modelo model, CrearCuenta regis) {
        this.view = view;
        this.model = model;
        this.regis = regis;
    }

    public void iniciar(){
        view.setTitle("Aplicacion Financiera");
        view.setLocationRelativeTo(null);
    }
}
