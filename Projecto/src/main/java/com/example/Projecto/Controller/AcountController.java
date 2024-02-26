package com.example.Projecto.Controller;

import com.example.Projecto.Model.Cuentas;
import com.example.Projecto.Service.AcountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Cuentas")
public class AcountController {

    @Autowired
    AcountService acountService;

    @PostMapping("/guardarCuenta")
    public Cuentas guardarCuenta(@RequestBody Cuentas cuentas){
        return this.acountService.guardarCuenta(cuentas);
    }

    @PostMapping("/Cuentas/modificarCuentas")
    public Cuentas modificarCuentas(@RequestBody Cuentas cuentas){
        return this.acountService.modificarCuentas(cuentas);
    }

    @GetMapping()
    public ArrayList<Cuentas> obtenerCuentas(){
        return acountService.obtenerCuentas();
    }

    @GetMapping("/CuentasId")
    public ArrayList<Cuentas> obtenerPorId(int idCuentas){
        return this.acountService.obtenerPorId(idCuentas);
    }

    @PostMapping("/Movimientos/Consignacion/{dinero}")
    public Cuentas consignarDinero(@PathVariable("dinero") int dinero,@RequestBody Cuentas cuentas){
        return this.acountService.consignarDinero(dinero, cuentas);
    }

    @PostMapping("/Movimientos/Retirar/{dinero}")
    public Cuentas retirarDinero(@PathVariable("dinero") int dinero,@RequestBody Cuentas cuentas){
        return this.acountService.retirarDinero(dinero, cuentas);
    }

    @Transactional
    @PostMapping("/Movimientos/Transferir/{Dinero}/{NumeroDeCuenta1}/{NumeroDeCuenta2}")
    public List<Cuentas> transferirDinero (@PathVariable("Dinero") int dinero, @PathVariable("NumeroDeCuenta1") Long numero1, @PathVariable("NumeroDeCuenta2") Long numero2){
        return this.acountService.transferirDinero(dinero, numero1, numero2);
    }

    /*
    @GetMapping("/id")
    public String obtenerId(int idCuentas){
        return this.acountService.obtenerId(idCuentas);
    }
*/
    @DeleteMapping("/Borrar")
    public void borrarCuenta(int idCliente, Cuentas cuentas){
        this.acountService.borrarCuenta(idCliente, cuentas);
    }
}
