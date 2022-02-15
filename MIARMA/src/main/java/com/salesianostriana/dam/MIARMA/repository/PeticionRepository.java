package com.salesianostriana.dam.MIARMA.repository;

import com.salesianostriana.dam.MIARMA.models.Peticion;
import com.salesianostriana.dam.MIARMA.models.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeticionRepository extends JpaRepository <Peticion, Long> {

    @EntityGraph(value = "grafo-peticion-user", type = EntityGraph.EntityGraphType.LOAD)
    List<Peticion> findByIdNotNull();
}
