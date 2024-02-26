package com.example.Projecto.Controller;

import com.example.Projecto.Model.Cuentas;
import com.example.Projecto.Service.AcountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

    @PostMapping("/Movimientos/Transferir/{dinero}")
    public void transferirDinero(@PathVariable("dinero") int dinero, @RequestBody Cuentas cuenta1,@RequestBody Cuentas cuenta2){
        this.acountService.transferirDinero(dinero, cuenta1, cuenta2);
    }

    /*
    @GetMapping("/id")
    public String obtenerId(int idCuentas){
        return this.acountService.obtenerId(idCuentas);
    }
*/
    @DeleteMapping("/Borrar/{idCliente}")
    public void borrarCuenta(@PathVariable("idCliente")int idCliente, Cuentas cuentas){
        this.acountService.borrarCuenta(idCliente, cuentas);
    }
}
