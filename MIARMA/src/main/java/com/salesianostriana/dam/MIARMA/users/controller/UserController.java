package com.salesianostriana.dam.MIARMA.users.controller;

import com.salesianostriana.dam.MIARMA.Dto.Post.CreatePostDto;
import com.salesianostriana.dam.MIARMA.users.dto.*;
import com.salesianostriana.dam.MIARMA.users.model.User;
import com.salesianostriana.dam.MIARMA.users.services.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserEntityService userEntityService;
    private final UserDtoConverter userDtoConverter;





    @PostMapping("/auth/register")
    public ResponseEntity<GetUserDto3> nuevoUsuario(@RequestPart("file")MultipartFile file, @RequestPart("user") CreateUserDto newPropietario) throws IOException {
        User saved = userEntityService.saveUser(newPropietario, file);

        if (saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(userDtoConverter.convertUserEntityToGetUserDto(saved));
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<GetUserDto3> visializarPerfif(@AuthenticationPrincipal User userPrincipal, @PathVariable UUID id){


        return ResponseEntity.status(HttpStatus.OK)
                .body(userEntityService.visializarPerfif(userPrincipal, id));
    }

    @PutMapping("/profile/me")
    public ResponseEntity<?> editUser(@RequestPart("file") MultipartFile file, @RequestPart("user") CreateUserDto createUserDto, @AuthenticationPrincipal User userPrincipal) throws IOException {
        User userEditado = userEntityService.userEdit(file, createUserDto, userPrincipal);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userDtoConverter.convertUserEntityToGetUserDto(userEditado));
    }






}
