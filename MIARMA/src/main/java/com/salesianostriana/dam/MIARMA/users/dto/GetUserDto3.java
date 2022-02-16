package com.salesianostriana.dam.MIARMA.users.dto;


import com.salesianostriana.dam.MIARMA.models.Estado;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserDto3 {
    private UUID id;
    private String nick;
    private String email;
    private Date fechaNacimiento;
    private String avatar;
    private Estado estado;
}
