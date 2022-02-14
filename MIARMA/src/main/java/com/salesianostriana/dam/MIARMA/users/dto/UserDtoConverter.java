package com.salesianostriana.dam.realstatev2.users.dto;

import com.salesianostriana.dam.realstatev2.users.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public GetUserDto convertUserEntityToGetUserDto(User user) {
        return GetUserDto.builder()
                .avatar(user.getAvatar())
                .nombre(user.getNombre())
                .email(user.getEmail())
                .role(user.getRoles().name())
                .id(user.getId())
                .build();


    }

}
