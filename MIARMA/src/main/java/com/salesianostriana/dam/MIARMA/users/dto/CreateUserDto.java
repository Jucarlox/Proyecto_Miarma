package com.salesianostriana.dam.MIARMA.users.dto;

import com.salesianostriana.dam.MIARMA.users.model.UserRole;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDto {

    private String nick;
    private String email;
    private Date fechaNacimiento;
    private String avatar;
    private boolean privacity;
    private String password;
    private String password2;

}
