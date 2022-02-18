package com.salesianostriana.dam.MIARMA.errores.excepciones;

public class FollowListNotFoundException extends EntityNotFoundException{
    public FollowListNotFoundException(String nick) {
        super(String.format("No sigues a %s ", nick));
    }
}
