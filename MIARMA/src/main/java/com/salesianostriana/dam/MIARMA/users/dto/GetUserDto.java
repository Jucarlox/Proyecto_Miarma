package com.salesianostriana.dam.MIARMA.users.dto;

import com.salesianostriana.dam.MIARMA.users.model.UserRole;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserDto {

    private String nick;
    private String email;
    private Date fechaNacimiento;
    private String avatar;



}