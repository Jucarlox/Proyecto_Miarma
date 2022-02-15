package com.salesianostriana.dam.MIARMA.users.dto;

import com.salesianostriana.dam.MIARMA.users.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public GetUserDto convertUserEntityToGetUserDto(User user) {
        return GetUserDto.builder()
                .avatar(user.getAvatar())
                .fechaNacimiento(user.getFechaNacimiento())
                .nick(user.getNick())
                .email(user.getEmail())

                .build();


    }

}
