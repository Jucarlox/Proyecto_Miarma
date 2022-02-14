package com.salesianostriana.dam.realstatev2.security.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtUserResponse {
    private String email;
    private String nombre;
    private String apellidos;
    private String role;
    private String token;
}
