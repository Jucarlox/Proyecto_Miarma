package com.salesianostriana.dam.realstatev2.users.repository;

import com.salesianostriana.dam.realstatev2.model.Vivienda;
import com.salesianostriana.dam.realstatev2.users.model.User;
import com.salesianostriana.dam.realstatev2.users.model.UserRole;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserEntityRepository extends JpaRepository<User, UUID> {

    Optional<User> findFirstByEmail(String email);
    Optional<User> findById(UUID id);
    List<User> findByRoles (UserRole roles);

    @EntityGraph("grafo-user-con-vivienda")
    List<User> findAll();

    @Query(value = "select *  from vivienda v where USER_ID = :userId" , nativeQuery=true)
    List<Vivienda> findAllViviendasToPropietario(UUID userId);



}
