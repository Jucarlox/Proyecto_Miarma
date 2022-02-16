package com.salesianostriana.dam.MIARMA.controller;

import com.salesianostriana.dam.MIARMA.Dto.Peticion.GetPublicacionDto;
import com.salesianostriana.dam.MIARMA.Dto.Peticion.PublicacionDtoConverter;
import com.salesianostriana.dam.MIARMA.models.Peticion;
import com.salesianostriana.dam.MIARMA.models.Post;
import com.salesianostriana.dam.MIARMA.services.impl.PeticionServiceImpl;
import com.salesianostriana.dam.MIARMA.users.dto.GetUserDto2;
import com.salesianostriana.dam.MIARMA.users.dto.UserDtoConverter;
import com.salesianostriana.dam.MIARMA.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PeticionController {

    private final PeticionServiceImpl peticionService;
    private final PublicacionDtoConverter publicacionDtoConverter;
    private final UserDtoConverter userDtoConverter;

    @PostMapping("/follow/{nick}")
    public ResponseEntity<GetPublicacionDto> solicitud(@AuthenticationPrincipal User user, @PathVariable String nick){

        Peticion peticion=peticionService.solicitud(user, nick);

        if (peticion == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(publicacionDtoConverter.convertPublicacionToGetPublicacionDto(peticion));
    }

    @PostMapping("/follow/accept/{id}")
    public ResponseEntity<?> aceptarSolicitud(@AuthenticationPrincipal User user, @PathVariable Long id){

        List<User> userList=peticionService.aceptarSolicitud(user, id);

        if (userList == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(userDtoConverter.convertUserEntityToGetUserDto3(userList));
    }

    @GetMapping("follow/list")
    public ResponseEntity<List<GetPublicacionDto>> listaPeticiones(){
        if (peticionService.findAll().isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            List<GetPublicacionDto> list = peticionService.findAll().stream()
                    .map(publicacionDtoConverter::convertPublicacionToGetPublicacionDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(list);
        }
    }
}
