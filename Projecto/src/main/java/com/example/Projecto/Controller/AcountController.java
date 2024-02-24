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
}
