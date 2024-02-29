package com.example.Projecto.DTO.Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RetirarConsignarDtoRequest {

    private int dinero;

    private String numeroCuenta;

}
