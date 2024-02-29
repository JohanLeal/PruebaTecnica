package com.example.Projecto.DTO.Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConsignarDtoRequest {

    private int dinero;

    private String numeroCuenta1;

    private String numeroCuenta2;
}
