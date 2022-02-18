package com.salesianostriana.dam.MIARMA.controller;

import com.salesianostriana.dam.MIARMA.Dto.Peticion.GetPeticionDto;
import com.salesianostriana.dam.MIARMA.Dto.Peticion.PeticionDtoConverter;
import com.salesianostriana.dam.MIARMA.models.Peticion;
import com.salesianostriana.dam.MIARMA.services.impl.PeticionServiceImpl;
import com.salesianostriana.dam.MIARMA.users.dto.UserDtoConverter;
import com.salesianostriana.dam.MIARMA.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PeticionController {

    private final PeticionServiceImpl peticionService;
    private final PeticionDtoConverter publicacionDtoConverter;
    private final UserDtoConverter userDtoConverter;

    @PostMapping("/follow/{nick}")
    public ResponseEntity<GetPeticionDto> solicitud(@AuthenticationPrincipal User user, @PathVariable String nick) {

        Peticion peticion = peticionService.solicitud(user, nick);

        if (peticion == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(publicacionDtoConverter.convertPeticionToGetPeticionDto(peticion));
    }

    @PostMapping("/follow/accept/{id}")
    public ResponseEntity<?> aceptarSolicitud(@AuthenticationPrincipal User user, @PathVariable Long id) {

        List<User> userList = peticionService.aceptarSolicitud(user, id);

        if (userList == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(userDtoConverter.convertUserEntityToGetUserDto3(userList));
    }

    @DeleteMapping("/follow/decline/{id}")
    public ResponseEntity<?> rechazarSolicitud(@PathVariable Long id) {

        peticionService.rechazarSolicitud(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("follow/list")
    public ResponseEntity<List<GetPeticionDto>> listaPeticiones() {
        if (peticionService.findAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<GetPeticionDto> list = peticionService.findAll().stream()
                    .map(publicacionDtoConverter::convertPeticionToGetPeticionDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(list);
        }
    }
}
