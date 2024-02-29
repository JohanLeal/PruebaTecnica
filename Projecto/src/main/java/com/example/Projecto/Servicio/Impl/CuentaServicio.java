package com.example.Projecto.Servicio.Impl;

import com.example.Projecto.DTO.Request.*;
import com.example.Projecto.Model.Clientes;
import com.example.Projecto.Model.Cuentas;
import com.example.Projecto.DTO.Response.ClienteDtoResponse;
import com.example.Projecto.DTO.Response.CuentaDtoResponse;
import com.example.Projecto.Repositorio.CuentaRepositorio;
import com.example.Projecto.Repositorio.ClienteRepositorio;
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
    CuentaRepositorio cuentaRepositorio;

    @Autowired
    ClienteRepositorio clienteRepositorio;

    //Se crea el metodo para guardar cuenta pidiendo los datos del clienteDtoRequest y cuentaDtoRequest
    public Object guardarCuenta(CuentaDtoRequest cuentaDtoRequest, ClienteDtoRequest clienteDtoRequest){
        //Se encuenta el cliente con el id proporcionado en el DTO
        Optional<Clientes> clientes = clienteRepositorio.findById(clienteDtoRequest.getIdClientes());
        //validacion de si el cliente existe
        if(clientes.isPresent()){
            //Se guarda la informacion en un objeto tipo cliente
            Clientes cliente = clientes.get();
            //Se hace la relacion para enlazar la cuenta
            List<Cuentas> cuentas = cliente.getIdCuentas();
            //Si el cliente no posee cuentas se realiza la accion
            if (cuentas.isEmpty()){
                cuentaRepositorio.save(insertarCampos(cuentaDtoRequest, clienteDtoRequest));
            }
            //Se valdia si el cuente tenga cuentas existentes, ya sea cuenta de ahorro, corriente o amabas
            for(Cuentas cuenta : cuentas){
                if (cuentaDtoRequest.getTipoCuenta().equalsIgnoreCase(cuenta.getTipoCuenta())){
                    return "Ya posee una cuenta";
                }else{
                    cuentaRepositorio.save(insertarCampos(cuentaDtoRequest, clienteDtoRequest));
                }
            }
        //Si no encontro el cliente se realiza la accion
        }else{
            return "No se encontro el cliente";
        }
        return "Se creo el la cuenta";
    }

    //Metodo para modificar cuentas
    public Object modificarCuentas(int idCuenta, ModificarCuentaDtoRequest modificarCuentaDtoRequest){
        //Se establecen los datos de auditoria
        LocalDate fechaActual = LocalDate.now();
        modificarCuentaDtoRequest.setFechaModificacionCuenta(String.valueOf(fechaActual));
        //Se llama al metodo aparte llamado "modificarCuenta" para que la informacion se confirme
        // y se guarde correctamente
        return cuentaRepositorio.save(modificarCuenta(idCuenta, modificarCuentaDtoRequest)) + "Se modifico el cliente";
    }

    //Metodo apra obtener cuentas mediante el idClientes
    public ClienteDtoResponse obtenerCuenta(int idClientes){
        //Se llama a un metodo externo para obtener el cliente por medio del idClientes
        return obtenerDatos(idClientes);
    }

    //Metodo para consignar dinero en una cuenta existente requiriendo los datos del retirarConsignarDtoRequest
    public Object consignarDinero(RetirarConsignarDtoRequest retirarConsignarDtoRequest){
        //Se obtiene la informacion de la cuenta por medio del numero de cuenta
        Cuentas cuentas = cuentaRepositorio.findByNumeroCuenta(retirarConsignarDtoRequest.getNumeroCuenta());
        //Se agrega la cantidad de dinero a la cuenta
        cuentas.setSaldo(cuentas.getSaldo() + retirarConsignarDtoRequest.getDinero());
        //Se guarda el estado de la cuenta con el cambio
        cuentaRepositorio.save(cuentas);

        return "Consignacion exitosa";
    }

    //Metodo para retirar dinero
    public Object retirarDinero(RetirarConsignarDtoRequest retirarConsignarDtoRequest){
        //Se obtiene la informacion de la cuenta por medio del numero de cuenta
        Cuentas cuentas = cuentaRepositorio.findByNumeroCuenta(retirarConsignarDtoRequest.getNumeroCuenta());
        //Se hace la validacion de si el dinero en la cuenta es suficiente para realizar la accion
        if ((cuentas.getSaldo() - retirarConsignarDtoRequest.getDinero())<0){
            return "No hay suficiente dinero";
        }else {
            //Si hay suficiente dinero se hace el retiro correcto del dinero
            cuentas.setSaldo(cuentas.getSaldo()-retirarConsignarDtoRequest.getDinero());
            cuentaRepositorio.save(cuentas);
        }
        return "Retiro exitoso";
    }


    //Metodo para transferir dinero entre dos cuentas
    public Object transferirDinero(ConsignarDtoRequest consignarDtoRequest) {
        //Se pide los 2 numeros de cuenta y se obtiene la informacion
        Cuentas cuenta1 = cuentaRepositorio.findByNumeroCuenta(consignarDtoRequest.getNumeroCuenta1());
        Cuentas cuenta2 = cuentaRepositorio.findByNumeroCuenta(consignarDtoRequest.getNumeroCuenta2());
        //Se hace la validacion del dinero
        if ((cuenta1.getSaldo() - consignarDtoRequest.getDinero()) < 0) {
            return "Dinero insuficiente";
        } else {
            //Se la resta y la suma del dinero en las cuentas
            cuenta1.setSaldo(cuenta1.getSaldo() - consignarDtoRequest.getDinero());
            cuenta2.setSaldo(cuenta2.getSaldo() + consignarDtoRequest.getDinero());
        }
        //Y por ultimo se guardan los cambios realizados
        cuentaRepositorio.save(cuenta1);
        cuentaRepositorio.save(cuenta2);
        return "Transferencia exitosa";
    }

    //Metodo para borrar cuentas validando si tienen dinero en ella
    public Object borrarCuenta(int idCuentas){
        //Se guarda la informacion de la cuenta
        Optional<Cuentas> cuentas = cuentaRepositorio.findById(idCuentas);
        //Se trae el dato del dinero en la cuenta
        int saldo = cuentas.get().getSaldo();
        //Se realiza la validacion
        if(saldo>0){
            return "Error eliminar: dinero disponible en la cuenta";
        }else{
            //Y si todo esta correcto se borra la cuenta
            cuentaRepositorio.deleteById(idCuentas);
        }
        return "Se borro la cuenta";
    }

    //Metodo que se usa en la creacion de cuenta para asignar un numero aleatorio al numero de la cuenta
    public Object generarNumeroCuenta(CuentaDtoRequest cuentaDtoRequest){
        //Se instancian los objetos a utilizar
        Random random = new Random();
        CuentaDtoResponse cuentaDtoResponse = new CuentaDtoResponse();
        //Se crea una lista con todas la cuentas creadas para validar si el numero de cuenta a crear
        // no es repetido
        List<Cuentas> cuentas = cuentaRepositorio.findAll();
        //Se hace la validacion para las cuentas de tipo ahorros
        if(cuentaDtoRequest.getTipoCuenta().equalsIgnoreCase("Ahorros")){
            String numeroCuentaRandom = String.valueOf(random.nextLong(99999999-10000000)+10000000);
            for (Cuentas cuenta : cuentas){
                if (cuenta.getNumeroCuenta().equalsIgnoreCase(numeroCuentaRandom)){
                    return "Numero de cuenta ya existe";
                }else{
                    String numeroCuenta = "53" + numeroCuentaRandom;
                    cuentaDtoResponse.setNumeroCuenta(numeroCuenta);
                }
            }aa
        //Se hace la validacion apra las cuentas de tipo corriente
        }else if(cuentaDtoRequest.getTipoCuenta().equalsIgnoreCase("Corriente")){

            String numeroCuentaRandom = String.valueOf(random.nextLong(99999999-10000000)+10000000);
            for (Cuentas cuenta : cuentas){
                if (cuenta.getNumeroCuenta().equalsIgnoreCase(numeroCuentaRandom)){
                    return "Numero de cuenta ya existe";
                }else{
                    String numeroCuenta = "33" + numeroCuentaRandom;
                    cuentaDtoResponse.setNumeroCuenta(numeroCuenta);
                }
            }
        }
        return cuentaDtoResponse;
    }

    public Cuentas insertarCampos(CuentaDtoRequest cuentaDtoRequest, ClienteDtoRequest clienteDtoRequest){
        Cuentas cuentas = new Cuentas();

        Clientes clientes = clienteRepositorio.findById(clienteDtoRequest.getIdClientes()).orElse(null);
        LocalDate fechaActual = LocalDate.now();
        CuentaDtoResponse cuentaDtoResponse = (CuentaDtoResponse) generarNumeroCuenta(cuentaDtoRequest);
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
        Clientes clientes = clienteRepositorio.findById(idClientes).orElse(null);
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
        Cuentas cuentas = cuentaRepositorio.findById(idCuenta).orElse(null);
        cuentas.setEstadoCuenta(modificarCuentaDtoRequest.getEstadoCuenta());
        cuentas.setExentaGMF(modificarCuentaDtoRequest.getExentaGMF());
        return cuentas;
    }

}
