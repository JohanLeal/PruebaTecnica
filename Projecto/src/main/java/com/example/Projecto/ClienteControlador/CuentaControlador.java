package com.example.Projecto.ClienteControlador;

import com.example.Projecto.DTO.Request.*;
import com.example.Projecto.DTO.Response.ClienteDtoResponse;
import com.example.Projecto.Servicio.ICuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Cuentas")
public class CuentaControlador  {

    @Autowired
    ICuenta iCuenta;

    @PostMapping("/guardarCuenta")
    public Object guardarCuenta(CuentaDtoRequest cuentaDtoRequest, ClienteDtoRequest clienteDtoRequest) {
        return iCuenta.guardarCuenta(cuentaDtoRequest, clienteDtoRequest);
    }

    @PutMapping("/modificarCuentas/{idCuenta}")
    public void modificarCuentas(int idCuenta, @RequestBody ModificarCuentaDtoRequest modificarCuentaDtoRequest){
        iCuenta.modificarCuentas(idCuenta, modificarCuentaDtoRequest);
    }

    @GetMapping()
    public ClienteDtoResponse obtenerCuenta(int idClientes){
        return iCuenta.obtenerCuenta(idClientes);
    }

    @PostMapping("/Movimientos/Consignacion")
    public Object consignarDinero(RetirarConsignarDtoRequest retirarConsignarDtoRequest){
        return iCuenta.consignarDinero(retirarConsignarDtoRequest);
    }

    @PostMapping("/Movimientos/Retirar")
    public void retirarDinero(RetirarConsignarDtoRequest retirarConsignarDtoRequest){
        this.iCuenta.retirarDinero(retirarConsignarDtoRequest);
    }

    @Transactional
    @PostMapping("/Movimientos/Transferir")
    public Object transferirDinero (ConsignarDtoRequest consignarDtoRequest){
        return iCuenta.transferirDinero(consignarDtoRequest);
    }

    @DeleteMapping("/Borrar")
    public Object borrarCuenta(int idCuentas){
        return iCuenta.borrarCuenta(idCuentas);
    }
}
