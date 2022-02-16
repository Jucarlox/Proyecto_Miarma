package com.salesianostriana.dam.MIARMA.repository;

import com.salesianostriana.dam.MIARMA.models.Estado;
import com.salesianostriana.dam.MIARMA.models.Peticion;
import com.salesianostriana.dam.MIARMA.models.Post;
import com.salesianostriana.dam.MIARMA.users.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByPrivacity (Estado estado);

    @EntityGraph(value = "grafo-post-user", type = EntityGraph.EntityGraphType.LOAD)
    List<Post> findByUser(User user);
}
