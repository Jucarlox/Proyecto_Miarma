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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl {

    private final StorageService storageService;
    private final PostRepository postRepository;

    public Post savePost(CreatePostDto newPost, MultipartFile file, User user) throws IOException {
        String filenameOriginal = storageService.original(file);
        String filenamePublicacion = storageService.publicacion(file);

        String uriPublicacion = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filenamePublicacion)
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
                .fileScale(uriPublicacion)
                .user(user)
                .build();
        return postRepository.save(post);
    }


    public Post editPost(CreatePostDto newPost, MultipartFile file, User user, Long id) throws IOException {


        Optional<Post> post = postRepository.findById(id);

        if(post.isEmpty()){
            return null;
        }else {
            post.get().setDescripcion(newPost.getDescripcion());
            post.get().setTitle(newPost.getTitle());
            if(!file.isEmpty()){


                String scale = StringUtils.cleanPath(String.valueOf(post.get().getFileScale())).replace("http://localhost:8080/download/","");
                Path path = storageService.load(scale);
                String filename = StringUtils.cleanPath(String.valueOf(path)).replace("http://localhost:8080/download/","");
                Path pathScalse = Paths.get(filename);
                storageService.deleteFile(pathScalse);


                String original = StringUtils.cleanPath(String.valueOf(post.get().getFileOriginal())).replace("http://localhost:8080/download/","");
                Path path2 = storageService.load(original);
                String filename2 = StringUtils.cleanPath(String.valueOf(path2)).replace("http://localhost:8080/download/","");
                Path pathOriginal = Paths.get(filename2);
                storageService.deleteFile(pathOriginal);

                String filenameOriginal = storageService.original(file);
                String filenamePublicacion = storageService.publicacion(file);

                String uriPublicacion = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/download/")
                        .path(filenamePublicacion)
                        .toUriString();

                String uriOriginal = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/download/")
                        .path(filenameOriginal)
                        .toUriString();

                post.get().setFileOriginal(uriOriginal);
                post.get().setFileScale(uriPublicacion);


            }
            return postRepository.save(post.get());
        }

    }
}
