package com.example.Projecto.Servicio;

import com.example.Projecto.DTO.Request.*;
import com.example.Projecto.DTO.Response.ClienteDtoResponse;
import com.example.Projecto.DTO.Response.CuentaDtoResponse;
import com.example.Projecto.Model.Cuentas;

public interface ICuenta {

    Object guardarCuenta(CuentaDtoRequest cuentaDtoRequest, ClienteDtoRequest clienteDtoRequest);

    Object modificarCuentas(int idCuenta, ModificarCuentaDtoRequest modificarCuentaDtoRequest);

    ClienteDtoResponse obtenerCuenta(int idClientes);

    Object consignarDinero(RetirarConsignarDtoRequest retirarConsignarDtoRequest);

    Object retirarDinero(RetirarConsignarDtoRequest retirarConsignarDtoRequest);

    Object transferirDinero(ConsignarDtoRequest consignarDtoRequest);

    Object borrarCuenta(int idCuentas);

    Object generarNumeroCuenta(CuentaDtoRequest cuentaDtoRequest);

    Cuentas insertarCampos(CuentaDtoRequest cuentaDtoRequest, ClienteDtoRequest clienteDtoRequest);

    ClienteDtoResponse obtenerDatos(int idClientes);

    Cuentas modificarCuenta(int idCuenta, ModificarCuentaDtoRequest modificarCuentaDtoRequest);
}
