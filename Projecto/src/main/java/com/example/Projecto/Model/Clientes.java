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

    private String tipoCuenta;

    private int numeroCuenta;

    private String estadoCuenta;

    private int saldo=0;

    private String exentaGMF;

    private String fechaCreacionCuenta;

    private String fechaModificacionCuenta;

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

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getEstadoCuenta() {
        return estadoCuenta;
    }

    public int getSaldo() {
        return saldo;
    }

    public String getExentaGMF() {
        return exentaGMF;
    }

    public String getFechaCreacionCuenta() {
        return fechaCreacionCuenta;
    }

    public String getFechaModificacionCuenta() {
        return fechaModificacionCuenta;
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

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setEstadoCuenta(String estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void setExentaGMF(String exentaGMF) {
        this.exentaGMF = exentaGMF;
    }

    public void setFechaCreacionCuenta(String fechaCreacionCuenta) {
        this.fechaCreacionCuenta = fechaCreacionCuenta;
    }

    public void setFechaModificacionCuenta(String fechaModificacionCuenta) {
        this.fechaModificacionCuenta = fechaModificacionCuenta;
    }
}
