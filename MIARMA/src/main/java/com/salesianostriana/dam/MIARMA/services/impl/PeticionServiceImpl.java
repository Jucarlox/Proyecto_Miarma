package com.salesianostriana.dam.MIARMA.services.impl;

import com.salesianostriana.dam.MIARMA.errores.excepciones.DynamicException;
import com.salesianostriana.dam.MIARMA.errores.excepciones.NickUserNotFoundException;
import com.salesianostriana.dam.MIARMA.errores.excepciones.SingleEntityNotFoundException;
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

    public Peticion solicitud(User userLogeado, String nick) {
        Optional<User> userBuscado = userEntityRepository.findByNick(nick);

        if (userBuscado.isPresent()) {
            if (!userBuscado.get().getNick().equals(userLogeado.getNick())) {


                Peticion peticion = Peticion.builder()
                        .user(userLogeado)
                        .build();


                userBuscado.get().addPeticion(peticion);
                peticionRepository.save(peticion);

                return peticion;
            } throw new DynamicException("No puedes mandarte solicitud a ti mismo");
        } else {
            throw new NickUserNotFoundException(nick, User.class);
        }
    }

    public List<User> aceptarSolicitud(User userLogeado, Long id) {

        Optional<Peticion> peticion = peticionRepository.findById(id);

        if (peticion.isPresent()) {

            userLogeado.addFollower(peticion.get().getUser());
            peticion.get().borrarDestinatarios();
            peticionRepository.deleteById(id);

            return userLogeado.getFollows();
        } else {
            throw new SingleEntityNotFoundException(id.toString(), Peticion.class);
        }
    }


    public void rechazarSolicitud(Long id) {

        Optional<Peticion> peticion = peticionRepository.findById(id);
        if (peticion.isPresent()) {
            peticionRepository.deleteById(id);
        } else {
            throw new SingleEntityNotFoundException(id.toString(), Peticion.class);
        }
    }

    public List<Peticion> findAll() {
        return peticionRepository.findAll();
    }
}
