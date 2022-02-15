package com.salesianostriana.dam.MIARMA.users.dto;

import com.salesianostriana.dam.MIARMA.Dto.Post.GetPostDto;
import com.salesianostriana.dam.MIARMA.users.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDtoConverter {

    public GetUserDto convertUserEntityToGetUserDto(User user) {


        return GetUserDto.builder()
                .id(user.getId())
                .avatar(user.getAvatar())
                .fechaNacimiento(user.getFechaNacimiento())
                .nick(user.getNick())
                .email(user.getEmail())
                .build();


    }

    public GetUserDto convertUserEntityToGetUserDto2(User user) {

        return GetUserDto.builder()
                .id(user.getId())
                .avatar(user.getAvatar())
                .fechaNacimiento(user.getFechaNacimiento())
                .nick(user.getNick())
                .email(user.getEmail())
                .postList(user.getPostList().stream().map(p -> new GetPostDto(p.getId(),p.getTitle(), p.getDescripcion(), p.getFileScale(), p.getPrivacity())).toList())
                .build();


    }



    public GetUserDto2 convertUserEntityToGetUserDto7(User user) {


        return GetUserDto2.builder()
                .nick(user.getNick())
                .email(user.getEmail())
                .build();


    }






    public List<GetUserDto2> convertUserEntityToGetUserDto3(List<User> userList) {


        return userList.stream().map(p-> convertUserEntityToGetUserDto7(p)).toList();


    }

}
