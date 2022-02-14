package com.salesianostriana.dam.realstatev2.users.services;

import com.salesianostriana.dam.realstatev2.model.Inmobiliaria;
import com.salesianostriana.dam.realstatev2.model.Vivienda;
import com.salesianostriana.dam.realstatev2.services.InmobiliariaService;
import com.salesianostriana.dam.realstatev2.services.base.BaseService;
import com.salesianostriana.dam.realstatev2.users.dto.CreateGestorDto;
import com.salesianostriana.dam.realstatev2.users.dto.CreateUserDto;
import com.salesianostriana.dam.realstatev2.users.model.User;
import com.salesianostriana.dam.realstatev2.users.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.salesianostriana.dam.realstatev2.users.repository.UserEntityRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import java.util.Optional;
import java.util.UUID;


@Service("userDetailsService")
@RequiredArgsConstructor
public class UserEntityService extends BaseService<User, UUID, UserEntityRepository> implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final InmobiliariaService inmobiliariaService;

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

    public User savePropietario(CreateUserDto newPropietario) {
        if (newPropietario.getPassword().contentEquals(newPropietario.getPassword2())) {
            User user = User.builder()
                    .password(passwordEncoder.encode(newPropietario.getPassword()))
                    .avatar(newPropietario.getAvatar())
                    .apellidos(newPropietario.getApellidos())
                    .direccion(newPropietario.getDireccion())
                    .telefono(newPropietario.getTelefono())
                    .nombre(newPropietario.getNombre())
                    .email(newPropietario.getEmail())
                    .roles(UserRole.PROPIETARIO)
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

    public User saveGestor(CreateGestorDto newGestor) {

        if (newGestor.getPassword().contentEquals(newGestor.getPassword2())) {
            User user = User.builder()
                    .password(passwordEncoder.encode(newGestor.getPassword()))
                    .avatar(newGestor.getAvatar())
                    .apellidos(newGestor.getApellidos())
                    .direccion(newGestor.getDireccion())
                    .telefono(newGestor.getTelefono())
                    .nombre(newGestor.getNombre())
                    .email(newGestor.getEmail())
                    .roles(UserRole.GESTOR)
                    .inmobiliaria(null)
                    .build();
            Optional<Inmobiliaria> inmobiliaria= inmobiliariaService.findById(newGestor.getInmobiliariaId());
            if(inmobiliaria.isPresent())
                user.addInmobiliaria(inmobiliaria.get());
            try{
                return save(user);
            }catch (DataIntegrityViolationException ex){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de ese usuario ya existe");
            }
        } else {
            return null;
        }
    }

    public User saveAdmin(CreateUserDto newAdmin) {
        if (newAdmin.getPassword().contentEquals(newAdmin.getPassword2())) {
            User user = User.builder()
                    .password(passwordEncoder.encode(newAdmin.getPassword()))
                    .avatar(newAdmin.getAvatar())
                    .apellidos(newAdmin.getApellidos())
                    .direccion(newAdmin.getDireccion())
                    .telefono(newAdmin.getTelefono())
                    .nombre(newAdmin.getNombre())
                    .email(newAdmin.getEmail())
                    .roles(UserRole.ADMIN)
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



    public User saveGestorWithoutId(CreateUserDto newGestor, Inmobiliaria inmobiliaria) {

        if (newGestor.getPassword().contentEquals(newGestor.getPassword2())) {
            User user = User.builder()
                    .password(passwordEncoder.encode(newGestor.getPassword()))
                    .avatar(newGestor.getAvatar())
                    .apellidos(newGestor.getApellidos())
                    .direccion(newGestor.getDireccion())
                    .telefono(newGestor.getTelefono())
                    .nombre(newGestor.getNombre())
                    .email(newGestor.getEmail())
                    .roles(UserRole.GESTOR)
                    .inmobiliaria(null)
                    .build();


                user.addInmobiliaria(inmobiliaria);
            try{
                return save(user);
            }catch (DataIntegrityViolationException ex){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de ese usuario ya existe");
            }
        } else {
            return null;
        }
    }


    public List<User> findAllUserVivienda() {
        return repositorio.findAll();
    }

    public List<Vivienda> propietariVivienda(UUID id){
        return repositorio.findAllViviendasToPropietario(id);
    }


}
