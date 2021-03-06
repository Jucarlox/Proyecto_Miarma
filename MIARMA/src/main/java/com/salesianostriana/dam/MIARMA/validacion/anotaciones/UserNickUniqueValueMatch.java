package com.salesianostriana.dam.MIARMA.validacion.anotaciones;


import com.salesianostriana.dam.MIARMA.validacion.validadores.UserNickUniqueValueMatchValitor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UserNickUniqueValueMatchValitor.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserNickUniqueValueMatch {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}