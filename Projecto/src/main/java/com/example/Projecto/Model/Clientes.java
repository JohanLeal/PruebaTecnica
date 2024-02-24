package com.example.Projecto.Model;

import jakarta.persistence.*;

@Entity
public class Clientes {

    @Id
    private Integer idClientes;

    private String TipoIdentificacion;

    private int numeroidentificacion;

    private String Nombres;

    private String Apellidos;

    private String Correo;

    private String Fecha_Nacimiento;

    private String Fecha_Creacion;

    private String Fecha_Modificacion;

    public Integer getIdClientes() {
        return idClientes;
    }

    public String getTipoIdentificacion() {
        return TipoIdentificacion;
    }

    public int getNumeroidentificacion() {
        return numeroidentificacion;
    }

    public String getNombres() {
        return Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getCorreo() {
        return Correo;
    }

    public String getFecha_Nacimiento() {
        return Fecha_Nacimiento;
    }

    public String getFecha_Creacion() {
        return Fecha_Creacion;
    }

    public String getFecha_Modificacion() {
        return Fecha_Modificacion;
    }

    public void setIdClientes(Integer idClientes) {
        this.idClientes = idClientes;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        TipoIdentificacion = tipoIdentificacion;
    }

    public void setNumeroidentificacion(int numeroidentificacion) {
        this.numeroidentificacion = numeroidentificacion;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public void setFecha_Nacimiento(String fecha_Nacimiento) {
        Fecha_Nacimiento = fecha_Nacimiento;
    }

    public void setFecha_Creacion(String fecha_Creacion) {
        Fecha_Creacion = fecha_Creacion;
    }

    public void setFecha_Modificacion(String fecha_Modificacion) {
        Fecha_Modificacion = fecha_Modificacion;
    }

}
