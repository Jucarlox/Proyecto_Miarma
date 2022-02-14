package com.salesianostriana.dam.realstatev2.users.controller;

import com.salesianostriana.dam.realstatev2.dto.propietario.GetPropietarioViviendaDto;
import com.salesianostriana.dam.realstatev2.dto.propietario.PropietarioDTOConverter;
import com.salesianostriana.dam.realstatev2.dto.vivienda.GetViviendaSummarizedDTO;
import com.salesianostriana.dam.realstatev2.dto.vivienda.ViviendaDTOConverter;
import com.salesianostriana.dam.realstatev2.dto.vivienda.ViviendaInteresDTO;
import com.salesianostriana.dam.realstatev2.model.Inmobiliaria;
import com.salesianostriana.dam.realstatev2.model.Interesa;
import com.salesianostriana.dam.realstatev2.model.Vivienda;
import com.salesianostriana.dam.realstatev2.services.InmobiliariaService;
import com.salesianostriana.dam.realstatev2.services.InteresaService;
import com.salesianostriana.dam.realstatev2.users.dto.CreateGestorDto;
import com.salesianostriana.dam.realstatev2.users.dto.CreateUserDto;
import com.salesianostriana.dam.realstatev2.users.dto.GetUserDto;
import com.salesianostriana.dam.realstatev2.users.dto.UserDtoConverter;
import com.salesianostriana.dam.realstatev2.users.model.User;
import com.salesianostriana.dam.realstatev2.users.model.UserRole;
import com.salesianostriana.dam.realstatev2.users.services.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserEntityService userEntityService;
    private final UserDtoConverter userDtoConverter;
    private final InmobiliariaService inmobiliariaService;
    private final InteresaService interesaService;
    private final PropietarioDTOConverter propietarioDTOConverter;
    private final ViviendaDTOConverter viviendaDTOConverter;

    @PostMapping("/auth/register/user")
    public ResponseEntity<GetUserDto> nuevoUsuario(@RequestBody CreateUserDto newPropietario) {
        User saved = userEntityService.savePropietario(newPropietario);

        if (saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(userDtoConverter.convertUserEntityToGetUserDto(saved));

    }

    @PostMapping("/auth/register/gestor")
    public ResponseEntity<GetUserDto> nuevoGestor(@RequestBody CreateGestorDto newGestor) {




        User saved= userEntityService.saveGestor(newGestor);

        if (saved == null || saved.getInmobiliaria()== null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(userDtoConverter.convertUserEntityToGetUserDto(saved));

    }

    @PostMapping("/auth/register/admin")
    public ResponseEntity<GetUserDto> nuevoAdmin(@RequestBody CreateUserDto newAdmin) {
        User saved = userEntityService.saveAdmin(newAdmin);

        if (saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(userDtoConverter.convertUserEntityToGetUserDto(saved));

    }


    @GetMapping("/interesado/")
    public ResponseEntity<List<User>> getInteresados (){

        List<Interesa> interesas= interesaService.findInteresados();
        List<User> interesados= new ArrayList<>();

        for (Interesa i: interesas){
            interesados.add(i.getInteresado());
        }
    return  ResponseEntity.ok(interesados);
    }


    @GetMapping("/interesado/{id}")
    public ResponseEntity<User> getInteresado (@PathVariable UUID id, @AuthenticationPrincipal User userLogged){

        List<Interesa> interesas= interesaService.findInteresados();
        Optional<User> interesado=null;


        for (Interesa i: interesas){
            if(i.getInteresado().getId().equals(id)){
                if(i.getInteresado().getId().equals(userLogged.getId()) || userLogged.getRoles().equals(UserRole.ADMIN))
                interesado = userEntityService.loadUserById(id);
            }
        }
        return  ResponseEntity.ok(interesado.get());
    }


    /////////////////////////////////////////////ACT CLASE/////////////////////////////////////////////////////////
    @GetMapping("/viviendas/propietario")
    public ResponseEntity<List<Vivienda>> findViviendasPropietario(@AuthenticationPrincipal User userLogged){
        //List<Vivienda> viviendas= userEntityService.propietariVivienda(userLogged.getId());
        List<Vivienda> viviendas= userLogged.getViviendas();
        if(viviendas.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok().body(viviendas);
        }
    }










    /////////////////////////////////////////////ACT CLASE/////////////////////////////////////////////////////////


}
