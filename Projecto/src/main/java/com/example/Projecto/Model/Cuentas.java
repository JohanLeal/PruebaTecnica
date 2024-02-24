package com.example.Projecto.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cuentas {

    @Id
    private int idClientes;

    private String tipoCuenta;

    private Long numeroCuenta;

    private String estadoCuenta;

    private int saldo=0;

    private String exentaGMF;

    private String fechaCreacionCuenta;

    private String fechaModificacionCuenta;

    public int getIdClientes() {
        return idClientes;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public Long getNumeroCuenta() {
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

    public void setIdClientes(int idClientes) {
        this.idClientes = idClientes;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public void setNumeroCuenta(Long numeroCuenta) {
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

    @Override
    public String toString() {
        return "Cuentas{" +
                "idClientes=" + idClientes +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", numeroCuenta=" + numeroCuenta +
                ", estadoCuenta='" + estadoCuenta + '\'' +
                ", saldo=" + saldo +
                ", exentaGMF='" + exentaGMF + '\'' +
                ", fechaCreacionCuenta='" + fechaCreacionCuenta + '\'' +
                ", fechaModificacionCuenta='" + fechaModificacionCuenta + '\'' +
                '}';
    }
}
