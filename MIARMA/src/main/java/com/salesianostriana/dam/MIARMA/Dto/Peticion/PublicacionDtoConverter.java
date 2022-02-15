package com.salesianostriana.dam.MIARMA.Dto.Peticion;

import com.salesianostriana.dam.MIARMA.Dto.Post.GetPostDto;
import com.salesianostriana.dam.MIARMA.models.Peticion;
import com.salesianostriana.dam.MIARMA.models.Post;
import org.springframework.stereotype.Component;
@Component
public class PublicacionDtoConverter {



        public GetPublicacionDto convertPublicacionToGetPublicacionDto(Peticion peticion) {
            return GetPublicacionDto.builder()
                    .id(peticion.getId())
                    .nick(peticion.getUser().getNick())
                    .idUser(peticion.getUser().getId())
                    .build();
        }


}
