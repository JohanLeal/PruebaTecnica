package com.example.Projecto.Servicio.Impl;

import com.example.Projecto.DTO.Request.*;
import com.example.Projecto.Model.Clientes;
import com.example.Projecto.Model.Cuentas;
import com.example.Projecto.DTO.Response.ClienteDtoResponse;
import com.example.Projecto.DTO.Response.CuentaDtoResponse;
import com.example.Projecto.Repositorio.CuentaRepositorio;
import com.example.Projecto.Repositorio.ClienteRpositorio;
import com.example.Projecto.Servicio.ICuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class CuentaServicio implements ICuenta {

    @Autowired
    CuentaRepositorio acountRepository;

    @Autowired
    ClienteRpositorio clientRepository;

    public Object guardarCuenta(CuentaDtoRequest cuentaDtoRequest, ClienteDtoRequest clienteDtoRequest){
        Optional<Clientes> clientes = clientRepository.findById(clienteDtoRequest.getIdClientes());
        if(clientes.isPresent()){
            Clientes cliente = clientes.get();
            List<Cuentas> cuentas = cliente.getIdCuentas();
            if (cuentas.isEmpty()){
                acountRepository.save(insertarCampos(cuentaDtoRequest, clienteDtoRequest));
            }
            for(Cuentas cuenta : cuentas){
                if (cuentaDtoRequest.getTipoCuenta().equalsIgnoreCase(cuenta.getTipoCuenta())){
                    return "Ya posee una cuenta";
                }else{
                    acountRepository.save(insertarCampos(cuentaDtoRequest, clienteDtoRequest));
                }
            }
        }else{
            return "No se encontro el cliente";
        }
        return "Se creo el la cuenta";
    }

    public Object modificarCuentas(int idCuenta, ModificarCuentaDtoRequest modificarCuentaDtoRequest){
        LocalDate fechaActual = LocalDate.now();
        modificarCuentaDtoRequest.setFechaModificacionCuenta(String.valueOf(fechaActual));
        return acountRepository.save(modificarCuenta(idCuenta, modificarCuentaDtoRequest)) + "Se modifico el cliente";
    }

    public ClienteDtoResponse obtenerCuenta(int idClientes){
        return obtenerDatos(idClientes);
    }

    public Object consignarDinero(RetirarConsignarDtoRequest retirarConsignarDtoRequest){
        Cuentas cuentas = acountRepository.findByNumeroCuenta(retirarConsignarDtoRequest.getNumeroCuenta());
        if(cuentas.getSaldo() - retirarConsignarDtoRequest.getDinero()<0){
            return "Dinero insuficiente";
        }else{
            cuentas.setSaldo(cuentas.getSaldo() + retirarConsignarDtoRequest.getDinero());
            acountRepository.save(cuentas);
        }
        return "Consignacion exitosa";
    }

    public Object retirarDinero(RetirarConsignarDtoRequest retirarConsignarDtoRequest){
        Cuentas cuentas = acountRepository.findByNumeroCuenta(retirarConsignarDtoRequest.getNumeroCuenta());
        if ((cuentas.getSaldo() - retirarConsignarDtoRequest.getDinero())<0){
            return "No hay suficiente dinero";
        }else {
            cuentas.setSaldo(cuentas.getSaldo()-retirarConsignarDtoRequest.getDinero());
            acountRepository.save(cuentas);
        }
        return "Retiro exitoso";
    }


    public Object transferirDinero(ConsignarDtoRequest consignarDtoRequest) {
        Cuentas cuenta1 = acountRepository.findByNumeroCuenta(consignarDtoRequest.getNumeroCuenta1());
        Cuentas cuenta2 = acountRepository.findByNumeroCuenta(consignarDtoRequest.getNumeroCuenta2());
        if ((cuenta1.getSaldo() - consignarDtoRequest.getDinero()) < 0) {
            return "Dinero insuficiente";
        } else {
            cuenta1.setSaldo(cuenta1.getSaldo() - consignarDtoRequest.getDinero());
            cuenta2.setSaldo(cuenta2.getSaldo() + consignarDtoRequest.getDinero());
        }
        acountRepository.save(cuenta1);
        acountRepository.save(cuenta2);
        return "Transferencia exitosa";
    }

    public Object borrarCuenta(int idCuentas){
        Optional<Cuentas> cuentas = acountRepository.findById(idCuentas);
        int saldo = cuentas.get().getSaldo();
        if(saldo>0){
            return "Error eliminar: dinero disponible en la cuenta";
        }else{
            acountRepository.deleteById(idCuentas);
        }
        return "Se borro la cuenta";
    }

    public CuentaDtoResponse generarNumeroCuenta(CuentaDtoRequest cuentaDtoRequest){
        Random random = new Random();
        CuentaDtoResponse cuentaDtoResponse = new CuentaDtoResponse();

        if(cuentaDtoRequest.getTipoCuenta().equalsIgnoreCase("Ahorros")){
            Long numeroCuentaRandom = random.nextLong(99999999-10000000)+10000000;
            String numeroCuenta = "53" + numeroCuentaRandom;

            cuentaDtoResponse.setNumeroCuenta(numeroCuenta);

        }else if(cuentaDtoRequest.getTipoCuenta().equalsIgnoreCase("Corriente")){
            Long numeroCuentaRandom = random.nextLong(99999999-10000000)+10000000;
            String numeroCuenta = "33" + numeroCuentaRandom;

            cuentaDtoResponse.setNumeroCuenta(numeroCuenta);
        }
        return cuentaDtoResponse;
    }

    public Cuentas insertarCampos(CuentaDtoRequest cuentaDtoRequest, ClienteDtoRequest clienteDtoRequest){
        Cuentas cuentas = new Cuentas();

        Clientes clientes = clientRepository.findById(clienteDtoRequest.getIdClientes()).orElse(null);
        LocalDate fechaActual = LocalDate.now();
        CuentaDtoResponse cuentaDtoResponse = generarNumeroCuenta(cuentaDtoRequest);
        cuentaDtoResponse.setExentaGMF("No");
        cuentaDtoResponse.setEstadoCuenta("Activo");
        cuentaDtoResponse.setFechaCreacionCuenta(String.valueOf((fechaActual)));
        cuentaDtoResponse.setFechaModificacionCuenta(String.valueOf(fechaActual));

        cuentas.setTipoCuenta(cuentaDtoRequest.getTipoCuenta());
        cuentas.setEstadoCuenta(cuentaDtoResponse.getEstadoCuenta());
        cuentas.setNumeroCuenta(cuentaDtoResponse.getNumeroCuenta());
        cuentas.setExentaGMF(cuentaDtoResponse.getExentaGMF());
        cuentas.setFechaCreacionCuenta(cuentaDtoResponse.getFechaCreacionCuenta());
        cuentas.setFechaModificacionCuenta(cuentaDtoResponse.getFechaModificacionCuenta());
        cuentas.setClientes(clientes);

        return cuentas;
    }

    public ClienteDtoResponse obtenerDatos(int idClientes){
        Clientes clientes = clientRepository.findById(idClientes).orElse(null);
        ClienteDtoResponse clienteDtoResponse = new ClienteDtoResponse();

        clienteDtoResponse.setIdClientes(clientes.getIdClientes());
        clienteDtoResponse.setTipoIdentificacion(clientes.getTipoIdentificacion());
        clienteDtoResponse.setNumeroidentificacion(clientes.getNumeroidentificacion());
        clienteDtoResponse.setNombres(clientes.getNombres());
        clienteDtoResponse.setApellidos(clientes.getApellidos());
        clienteDtoResponse.setCorreo(clientes.getCorreo());
        clienteDtoResponse.setFecha_Nacimiento(clientes.getFecha_Nacimiento());
        clienteDtoResponse.setFecha_Creacion(clientes.getFecha_Creacion());
        clienteDtoResponse.setFecha_Modificacion(clientes.getFecha_Modificacion());

        List<Cuentas> cuentas = clientes.getIdCuentas();

        List<CuentaDtoResponse> cuentasDto = new ArrayList<>();

        for (Cuentas cuenta : cuentas){
            CuentaDtoResponse cuentaDto = new CuentaDtoResponse();
            cuentaDto.setIdCuentas(cuenta.getIdCuentas());
            cuentaDto.setTipoCuenta(cuenta.getTipoCuenta());
            cuentaDto.setEstadoCuenta(cuenta.getEstadoCuenta());
            cuentaDto.setNumeroCuenta(cuenta.getNumeroCuenta());
            cuentaDto.setSaldo(cuenta.getSaldo());
            cuentaDto.setExentaGMF(cuenta.getExentaGMF());
            cuentaDto.setFechaCreacionCuenta(cuenta.getFechaCreacionCuenta());
            cuentaDto.setFechaModificacionCuenta(cuenta.getFechaModificacionCuenta());

            cuentasDto.add(cuentaDto);
        }

        clienteDtoResponse.setIdCuentas(cuentasDto);
        return clienteDtoResponse;
    }

    public Cuentas modificarCuenta(int idCuenta, ModificarCuentaDtoRequest modificarCuentaDtoRequest){
        Cuentas cuentas = acountRepository.findById(idCuenta).orElse(null);
        cuentas.setEstadoCuenta(modificarCuentaDtoRequest.getEstadoCuenta());
        cuentas.setExentaGMF(modificarCuentaDtoRequest.getExentaGMF());
        return cuentas;
    }

}
