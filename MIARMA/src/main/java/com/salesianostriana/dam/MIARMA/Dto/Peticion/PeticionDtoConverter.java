package com.salesianostriana.dam.MIARMA.Dto.Peticion;

import com.salesianostriana.dam.MIARMA.models.Peticion;
import org.springframework.stereotype.Component;

@Component
public class PeticionDtoConverter {


    public GetPeticionDto convertPeticionToGetPeticionDto(Peticion peticion) {
        return GetPeticionDto.builder()
                .id(peticion.getId())
                .nick(peticion.getUser().getNick())
                .idUser(peticion.getUser().getId())
                .build();
    }


}
