package com.salesianostriana.dam.MIARMA.errores.excepciones;

public class DynamicException extends EntityNotFoundException{
    public DynamicException(String mensaje) {
        super(mensaje);
    }
}
