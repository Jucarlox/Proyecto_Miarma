package com.salesianostriana.dam.MIARMA.Dto.Peticion;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPublicacionDto {
    private Long id;
    private String nick;
    private UUID idUser;
}
