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
/*
    public void add() {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO cliente (idCliente, TipoIdentificacion, NumeroIdentificacion, Nombres, Apellidos, Correo, FechaNacimiento, FechaCreacion, FechaModificacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            ps.setString(1, "31232154");
            ps.setString(2, "123");
            ps.setString(3, "123123");
            ps.setString(4, "Johan");
            ps.setString(5, "Leal");
            ps.setString(6, "uwu@gmail.com");
            ps.setString(7, "2004-11-22");
            ps.setString(8, "2024-02-19");
            ps.setString(9, "2024-02-19");

            // Execute the INSERT statement
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Row inserted successfully.");
            } else {
                System.out.println("Failed to insert row.");
            }
        } catch (SQLException e) {
            System.out.println("Error inserting row: " + e.getMessage());
        }
    }
    */

}
