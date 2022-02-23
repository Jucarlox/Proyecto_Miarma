package com.salesianostriana.dam.MIARMA.Dto.Post;

import com.salesianostriana.dam.MIARMA.models.Post;
import com.salesianostriana.dam.MIARMA.users.dto.GetUserDto;
import com.salesianostriana.dam.MIARMA.users.dto.UserDtoConverter;
import com.salesianostriana.dam.MIARMA.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostDtoConverter {

    private final UserDtoConverter userDtoConverter;
    public GetPostDto convertPostToGetPostDto(Post post) {
        return GetPostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .descripcion(post.getDescripcion())
                .privacity(post.getPrivacity())
                .fileScale(post.getFileScale())
                .user(userDtoConverter.convertUserEntityToGetUserDto(post.getUser()))
                .build();
    }

}
