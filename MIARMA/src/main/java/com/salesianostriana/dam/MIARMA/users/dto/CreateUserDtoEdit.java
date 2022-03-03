package com.salesianostriana.dam.MIARMA.users.dto;

import com.salesianostriana.dam.MIARMA.validacion.anotaciones.UserEmailUniqueValueMatch;
import com.salesianostriana.dam.MIARMA.validacion.anotaciones.UserNickUniqueValueMatch;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDtoEdit {

    private LocalDate fechaNacimiento;
    private String avatar;
    private boolean privacity;
    @NotEmpty
    private String password;
}