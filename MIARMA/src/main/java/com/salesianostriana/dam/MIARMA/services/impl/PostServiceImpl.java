package com.salesianostriana.dam.MIARMA.services.impl;

import com.salesianostriana.dam.MIARMA.Dto.Post.CreatePostDto;
import com.salesianostriana.dam.MIARMA.Dto.Post.GetPostDto;
import com.salesianostriana.dam.MIARMA.Dto.Post.PostDtoConverter;
import com.salesianostriana.dam.MIARMA.errores.excepciones.*;
import com.salesianostriana.dam.MIARMA.models.Estado;
import com.salesianostriana.dam.MIARMA.models.Post;
import com.salesianostriana.dam.MIARMA.repository.PostRepository;
import com.salesianostriana.dam.MIARMA.services.StorageService;
import com.salesianostriana.dam.MIARMA.users.dto.CreateUserDto;
import com.salesianostriana.dam.MIARMA.users.model.User;
import com.salesianostriana.dam.MIARMA.users.model.UserRole;
import com.salesianostriana.dam.MIARMA.users.repository.UserEntityRepository;
import io.github.techgnious.exception.VideoException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl {

    private final StorageService storageService;
    private final PostRepository postRepository;
    private final UserEntityRepository userEntityRepository;
    private final PostDtoConverter postDtoConverter;

    public Post savePost(CreatePostDto newPost, MultipartFile file, User user) throws IOException, VideoException {
        String filenameOriginal = storageService.original(file);

        String videoExtension = "mp4";

        String extension = StringUtils.getFilenameExtension(StringUtils.cleanPath(file.getOriginalFilename()));
        List<String> imagenExtension = Arrays.asList("png", "gif", "jpg", "svg");
        String filenamePublicacion;
        List<String> allExtension = Arrays.asList("png", "gif", "jpg", "svg", "mp4");

        if (imagenExtension.contains(extension)) {
            filenamePublicacion = storageService.escalado(file, 1024);

        } else if (videoExtension.equals(extension)) {
            filenamePublicacion = storageService.videoEscalado(file);
        } else {

            throw new UnsupportedMediaType(allExtension);
        }


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


    public Post editPost(CreatePostDto newPost, MultipartFile file, User user, Long id) throws IOException, VideoException {


        Optional<Post> post = postRepository.findById(id);

        if (post.isEmpty()) {
            throw new SingleEntityNotFoundException(id.toString(), Post.class);
        } else {
            post.get().setDescripcion(newPost.getDescripcion());
            post.get().setTitle(newPost.getTitle());
            post.get().setPrivacity(newPost.isPrivacity() ? Estado.PRIVADO : Estado.PUBLICO);


            String videoExtension = "mp4";
            String extension = StringUtils.getFilenameExtension(StringUtils.cleanPath(file.getOriginalFilename()));
            List<String> allExtension = Arrays.asList("png", "gif", "jpg", "svg", "mp4");

            if (!allExtension.contains(extension)) {
                throw new UnsupportedMediaType(allExtension);
            } else {
                String scale = StringUtils.cleanPath(String.valueOf(post.get().getFileScale())).replace("http://localhost:8080/download/", "")
                        .replace("%20", " ");
                Path path = storageService.load(scale);
                String filename = StringUtils.cleanPath(String.valueOf(path)).replace("http://localhost:8080/download/", "")
                        .replace("%20", " ");
                Path pathScalse = Paths.get(filename);
                storageService.deleteFile(pathScalse);


                String original = StringUtils.cleanPath(String.valueOf(post.get().getFileOriginal())).replace("http://localhost:8080/download/", "")
                        .replace("%20", " ");
                Path path2 = storageService.load(original);
                String filename2 = StringUtils.cleanPath(String.valueOf(path2)).replace("http://localhost:8080/download/", "")
                        .replace("%20", " ");
                Path pathOriginal = Paths.get(filename2);
                storageService.deleteFile(pathOriginal);


                String filenameOriginal = storageService.original(file);
                String filenamePublicacion;
                if (!videoExtension.contains(extension)) {
                    filenamePublicacion = storageService.escalado(file, 1024);

                } else {
                    filenamePublicacion = storageService.videoEscalado(file);
                }

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


    public ResponseEntity deletePost(User user, Long id) throws IOException {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            throw new SingleEntityNotFoundException(id.toString(), Post.class);
        } else {
            String scale = StringUtils.cleanPath(String.valueOf(post.get().getFileScale())).replace("http://localhost:8080/download/", "")
                    .replace("%20", " ");
            Path path = storageService.load(scale);
            String filename = StringUtils.cleanPath(String.valueOf(path)).replace("http://localhost:8080/download/", "")
                    .replace("%20", " ");
            Path pathScalse = Paths.get(filename);
            storageService.deleteFile(pathScalse);


            String original = StringUtils.cleanPath(String.valueOf(post.get().getFileOriginal())).replace("http://localhost:8080/download/", "")
                    .replace("%20", " ");
            Path path2 = storageService.load(original);
            String filename2 = StringUtils.cleanPath(String.valueOf(path2)).replace("http://localhost:8080/download/", "")
                    .replace("%20", " ");
            Path pathOriginal = Paths.get(filename2);
            storageService.deleteFile(pathOriginal);

            postRepository.deleteById(id);

            return ResponseEntity.noContent().build();

        }
    }

    public List<GetPostDto> getAllPostPublic() throws IOException {
        List<Post> postList = postRepository.findByPrivacity(Estado.PUBLICO);

        if (postList.isEmpty()) {
            throw new ListEntityNotFoundException(Post.class);
        } else {
            List<GetPostDto> getPostDto = postList.stream().map(p -> new GetPostDto(p.getId(),
                    p.getTitle(), p.getDescripcion(), p.getFileScale(), p.getPrivacity())).toList();
            return getPostDto;
        }

    }

    public Post getPostById(Long id, User user) {

        Optional<Post> post = postRepository.findById(id);

        if (post.isEmpty()) {
            throw new SingleEntityNotFoundException(id.toString(), Post.class);
        } else {
            if (post.get().getPrivacity().equals(Estado.PUBLICO)) {
                return post.get();
            } else {

                for (User usuarioDeLaLista : userEntityRepository.findFollowers(post.get().getUser().getId())) {
                    if (usuarioDeLaLista.getId().equals(user.getId())) {
                        return post.get();
                    }
                }
                throw new FollowListNotFoundException(post.get().getUser().getNick());
            }
        }
    }

    public List<GetPostDto> getPostListByNick(String nick, User user) {

        List<Post> publicacionList = postRepository.findAll();
        List<Post> publicacionList1 = postRepository.findByUserNick(nick);
        List<Post> publicacionList2 = postRepository.busquedaPorNick(Estado.PUBLICO, nick);
        Optional<User> userEntity = userEntityRepository.findByNick(nick);
        Optional<User> seguidor = userEntityRepository.findByFollowsContains(user);

        if (userEntity.isEmpty()) {
            throw new NickUserNotFoundException(nick, User.class);
        } else {
            if (publicacionList.isEmpty() && publicacionList1.isEmpty() && publicacionList2.isEmpty()) {
                return Collections.EMPTY_LIST;
            } else if (!userEntity.equals(seguidor)) {

                return publicacionList2.stream().map(postDtoConverter::convertPostToGetPostDto).collect(Collectors.toList());

            } else {
                return publicacionList1.stream().map(postDtoConverter::convertPostToGetPostDto).collect(Collectors.toList());
            }
        }
    }
}
