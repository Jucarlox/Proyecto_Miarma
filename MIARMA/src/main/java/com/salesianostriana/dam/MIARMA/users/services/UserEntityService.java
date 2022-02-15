package com.salesianostriana.dam.MIARMA.users.services;


import com.salesianostriana.dam.MIARMA.services.StorageService;
import com.salesianostriana.dam.MIARMA.services.base.BaseService;
import com.salesianostriana.dam.MIARMA.users.dto.CreateUserDto;

import com.salesianostriana.dam.MIARMA.users.model.User;
import com.salesianostriana.dam.MIARMA.users.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.salesianostriana.dam.MIARMA.users.repository.UserEntityRepository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;

import java.util.Optional;
import java.util.UUID;


@Service("userDetailsService")
@RequiredArgsConstructor
public class UserEntityService extends BaseService<User, UUID, UserEntityRepository> implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final StorageService storageService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repositorio.findFirstByEmail(email).orElseThrow(()-> new UsernameNotFoundException("El "+ email + " no encontrado"));
    }


    public List<User> loadUserByRol(UserRole roles) throws UsernameNotFoundException {
        return this.repositorio.findByRoles(roles);
    }

    public Optional<User> loadUserById(UUID id) throws UsernameNotFoundException {
        return this.repositorio.findById(id);
    }

    public User saveUser(CreateUserDto newUser, MultipartFile file) throws IOException {


        String filename = storageService.avatar(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();


        if (newUser.getPassword().contentEquals(newUser.getPassword2())) {
            User user = User.builder()
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .avatar(uri)
                    .nick(newUser.getNick())
                    .email(newUser.getEmail())
                    .fechaNacimiento(newUser.getFechaNacimiento())
                    .roles(UserRole.USER)
                    .build();
            try{
                return save(user);
            }catch (DataIntegrityViolationException ex){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de ese usuario ya existe");
            }
        } else {
            return null;
        }
    }













}
