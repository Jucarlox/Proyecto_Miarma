package com.salesianostriana.dam.MIARMA.users.dto;


import com.salesianostriana.dam.MIARMA.validacion.anotaciones.UserEmailUniqueValueMatch;
import com.salesianostriana.dam.MIARMA.validacion.anotaciones.UserNickUniqueValueMatch;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CreateUserDto {

    @UserNickUniqueValueMatch(message = "Ya hay otro usuario con ese nick")
    private String nick;
    @UserEmailUniqueValueMatch(message = "Ya hay otro usuario con ese email")
    private String email;
    private Date fechaNacimiento;
    private String avatar;
    private boolean privacity;
    private String password;
    private String password2;
}
