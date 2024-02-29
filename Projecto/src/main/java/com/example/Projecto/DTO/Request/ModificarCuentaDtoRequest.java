package com.example.Projecto.DTO.Request;

import com.example.Projecto.Model.Clientes;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ModificarCuentaDtoRequest {

    private String estadoCuenta;

    private String exentaGMF;

    private String fechaModificacionCuenta;
}
