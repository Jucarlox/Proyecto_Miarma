package com.salesianostriana.dam.MIARMA.services.impl;

import com.salesianostriana.dam.MIARMA.models.Peticion;
import com.salesianostriana.dam.MIARMA.repository.PeticionRepository;
import com.salesianostriana.dam.MIARMA.users.model.User;
import com.salesianostriana.dam.MIARMA.users.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeticionServiceImpl {

    private final UserEntityRepository userEntityRepository;
    private final PeticionRepository peticionRepository;

    public Peticion solicitud (User userLogeado, String nick){
        Optional<User> userBuscado = userEntityRepository.findByNick(nick);

        if(userBuscado.isPresent()){
            Peticion peticion = Peticion.builder()
                    .user(userLogeado)
                    .build();


            userBuscado.get().addPeticion(peticion);
            peticionRepository.save(peticion);

            return peticion;
        }else {
            return null;
        }
    }

    public List<User> aceptarSolicitud (User userLogeado, Long id){

        Optional<Peticion> peticion = peticionRepository.findById(id);

        if(peticion.isPresent()){

            userLogeado.addFollower(peticion.get().getUser());
            peticion.get().borrarDestinatarios();
            peticionRepository.deleteById(id);

            return userLogeado.getFollowers();
        }else{
            return null;
        }
    }

    public List<Peticion> findAll (){

        return peticionRepository.findAll();

    }
}
