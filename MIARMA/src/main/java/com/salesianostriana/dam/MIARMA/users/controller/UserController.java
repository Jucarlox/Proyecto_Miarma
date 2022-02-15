package com.salesianostriana.dam.MIARMA.users.controller;

import com.salesianostriana.dam.MIARMA.users.dto.CreateUserDto;
import com.salesianostriana.dam.MIARMA.users.dto.GetUserDto;
import com.salesianostriana.dam.MIARMA.users.model.User;
import com.salesianostriana.dam.MIARMA.users.dto.UserDtoConverter;
import com.salesianostriana.dam.MIARMA.users.services.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserEntityService userEntityService;
    private final UserDtoConverter userDtoConverter;




    @PostMapping("/auth/register")
    public ResponseEntity<GetUserDto> nuevoUsuario(@RequestPart("file")MultipartFile file, @RequestPart("user") CreateUserDto newPropietario) {
        User saved = userEntityService.saveUser(newPropietario, file);

        if (saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(userDtoConverter.convertUserEntityToGetUserDto(saved));
    }



}
