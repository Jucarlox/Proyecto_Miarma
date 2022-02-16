package com.salesianostriana.dam.MIARMA.controller;

import com.salesianostriana.dam.MIARMA.Dto.Post.CreatePostDto;
import com.salesianostriana.dam.MIARMA.Dto.Post.GetPostDto;
import com.salesianostriana.dam.MIARMA.Dto.Post.PostDtoConverter;
import com.salesianostriana.dam.MIARMA.models.Post;
import com.salesianostriana.dam.MIARMA.services.impl.PostServiceImpl;
import com.salesianostriana.dam.MIARMA.users.dto.GetUserDto;
import com.salesianostriana.dam.MIARMA.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl postService;
    private final PostDtoConverter postDtoConverter;


    @PostMapping("/post")
    public ResponseEntity<?> nuevoPost(@RequestPart("file") MultipartFile file, @RequestPart("post") CreatePostDto createPostDto, @AuthenticationPrincipal User userPrincipal) throws IOException {
        Post saved = postService.savePost(createPostDto, file, userPrincipal);

        if (saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(postDtoConverter.convertPostToGetPostDto(saved));
    }


    @PutMapping("/post/{id}")
    public ResponseEntity<?> editPost(@RequestPart("file") MultipartFile file, @RequestPart("post") CreatePostDto createPostDto, @AuthenticationPrincipal User userPrincipal, @PathVariable Long id) throws IOException {
        Post saved = postService.editPost(createPostDto, file, userPrincipal, id);

        if (saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(postDtoConverter.convertPostToGetPostDto(saved));
    }


    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deletePost(@AuthenticationPrincipal User userPrincipal, @PathVariable Long id) throws IOException {
        return postService.deletePost(userPrincipal, id);
    }

    @GetMapping("/post/public")
    public ResponseEntity<List<GetPostDto>> getAllPostPublic() throws IOException {
        return ResponseEntity.ok().body(postService.getAllPostPublic());
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<GetPostDto> getPostById(@PathVariable Long id, @AuthenticationPrincipal User user) throws IOException {

        Post post = postService.getPostById(id, user);

        if(post==null){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok().body(postDtoConverter.convertPostToGetPostDto(post));
        }

    }



}
