package com.salesianostriana.dam.realstatev2.users.dto;

import com.salesianostriana.dam.realstatev2.users.model.UserRole;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserDto {

    private String avatar;
    private String nombre;
    private String email;
    private String role;
    private UUID id;


}