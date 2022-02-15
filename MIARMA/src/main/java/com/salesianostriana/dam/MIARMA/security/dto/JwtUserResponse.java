package com.salesianostriana.dam.MIARMA.security.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtUserResponse {
    private String email;
    private String nike;

    private String role;
    private String token;
}
