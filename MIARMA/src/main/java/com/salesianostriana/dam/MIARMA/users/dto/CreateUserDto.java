package com.salesianostriana.dam.MIARMA.users.dto;


import com.salesianostriana.dam.MIARMA.validacion.anotaciones.PasswordsMatch;
import com.salesianostriana.dam.MIARMA.validacion.anotaciones.UserEmailUniqueValueMatch;
import com.salesianostriana.dam.MIARMA.validacion.anotaciones.UserNickUniqueValueMatch;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@PasswordsMatch(
        passwordField = "password",
        verifyPasswordField = "password2",
        message = "Las contraseñas no coinciden"
)
public class CreateUserDto {

    @UserNickUniqueValueMatch(message = "Ya hay otro usuario con ese nick")
    private String nick;
    @UserEmailUniqueValueMatch(message = "Ya hay otro usuario con ese email")
    @Email
    private String email;


    private Date fechaNacimiento;
    private String avatar;
    private boolean privacity;
    @NotEmpty
    private String password;
    private String password2;
}
