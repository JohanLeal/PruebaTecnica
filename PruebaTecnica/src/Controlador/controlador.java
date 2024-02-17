package Controlador;

import java.awt.event.ActionListener;
import Modelo.modelo;
import Vista.vista;
import java.awt.event.ActionEvent;

public class controlador implements ActionListener {
    
    private vista view;
    private modelo model;

    public controlador(vista view, modelo model) {
        this.view = view;
        this.model = model;
    }
    
    public void iniciar(){
        view.setTitle("Aplicacion Financiera");
        view.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
