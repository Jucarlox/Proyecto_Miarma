package com.salesianostriana.dam.realstatev2.security;

import com.salesianostriana.dam.realstatev2.security.dto.JwtUserResponse;
import com.salesianostriana.dam.realstatev2.security.dto.LoginDto;
import com.salesianostriana.dam.realstatev2.security.jwt.JwtProvider;
import com.salesianostriana.dam.realstatev2.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        Authentication authentication= authenticationManager.authenticate(
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
                .nombre(user.getNombre())
                .email(user.getEmail())
                .apellidos(user.getApellidos())
                .role(user.getRoles().toString())
                .token(jwt)
                .build();
    }
}
