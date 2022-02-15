package com.salesianostriana.dam.MIARMA.Dto.Post;

import com.salesianostriana.dam.MIARMA.models.Post;
import com.salesianostriana.dam.MIARMA.users.dto.GetUserDto;
import com.salesianostriana.dam.MIARMA.users.model.User;
import org.springframework.stereotype.Component;

@Component
public class PostDtoConverter {

    public GetPostDto convertPostToGetPostDto(Post post) {
        return GetPostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .descripcion(post.getDescripcion())
                .privacity(post.getPrivacity())
                .fileScale(post.getFileScale())
                .build();


    }

}
