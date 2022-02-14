package com.salesianostriana.dam.realstatev2.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDto {

    private String password;
    private String email;
}
