package com.salesianostriana.dam.MIARMA.services.impl;

import com.salesianostriana.dam.MIARMA.Dto.Post.CreatePostDto;
import com.salesianostriana.dam.MIARMA.models.Estado;
import com.salesianostriana.dam.MIARMA.models.Post;
import com.salesianostriana.dam.MIARMA.repository.PostRepository;
import com.salesianostriana.dam.MIARMA.services.StorageService;
import com.salesianostriana.dam.MIARMA.users.dto.CreateUserDto;
import com.salesianostriana.dam.MIARMA.users.model.User;
import com.salesianostriana.dam.MIARMA.users.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PostServiceImpl {

    private final StorageService storageService;
    private final PostRepository postRepository;

    public Post savePost(CreatePostDto newPost, MultipartFile file, User user) throws IOException {
        String filenameOriginal = storageService.original(file);
        String filenameAvatar = storageService.avatar(file);

        String uriAvatar = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filenameAvatar)
                .toUriString();

        String uriOriginal = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filenameOriginal)
                .toUriString();

        Post post = Post.builder()
                .title(newPost.getTitle())
                .descripcion(newPost.getDescripcion())
                .privacity(newPost.isPrivacity() ? Estado.PRIVADO : Estado.PUBLICO)
                .fileOriginal(uriOriginal)
                .fileScale(uriAvatar)
                .user(user)
                .build();
        return postRepository.save(post);
    }
}
