package com.salesianostriana.dam.MIARMA.security;

import com.salesianostriana.dam.MIARMA.models.Post;
import com.salesianostriana.dam.MIARMA.repository.PostRepository;
import com.salesianostriana.dam.MIARMA.security.dto.JwtUserResponse;
import com.salesianostriana.dam.MIARMA.security.dto.LoginDto;
import com.salesianostriana.dam.MIARMA.security.jwt.JwtProvider;
import com.salesianostriana.dam.MIARMA.users.dto.GetUserDto;
import com.salesianostriana.dam.MIARMA.users.dto.GetUserDto3;
import com.salesianostriana.dam.MIARMA.users.dto.UserDtoConverter;
import com.salesianostriana.dam.MIARMA.users.model.User;
import com.salesianostriana.dam.MIARMA.security.dto.JwtUserResponse;
import com.salesianostriana.dam.MIARMA.security.dto.LoginDto;
import com.salesianostriana.dam.MIARMA.security.jwt.JwtProvider;
import com.salesianostriana.dam.MIARMA.users.model.User;
import com.salesianostriana.dam.MIARMA.users.services.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserDtoConverter userDtoConverter;
    private final PostRepository postRepository;
    private final UserEntityService userEntityService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);


        String jwt = jwtProvider.generateToken(authentication);


        User user = (User) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertUserToJwtUserResponse(user, jwt));

    }

    private JwtUserResponse convertUserToJwtUserResponse(User user, String jwt) {
        return JwtUserResponse.builder()
                .nike(user.getNick())
                .email(user.getEmail())
                .role(user.getRoles().toString())
                .id(user.getId())
                .token(jwt)
                .build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(@AuthenticationPrincipal User userPrincipal) {

        GetUserDto getUserDto = userEntityService.visializarPerfif(userPrincipal, userPrincipal.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(getUserDto);
    }


}
