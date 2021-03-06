package com.salesianostriana.dam.MIARMA.security.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtUserResponse {
    private String email;
    private String nike;
    private UUID id;
    private String role;
    private String token;
}
