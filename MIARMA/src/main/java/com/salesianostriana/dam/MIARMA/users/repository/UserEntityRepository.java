package com.salesianostriana.dam.MIARMA.users.repository;


import com.salesianostriana.dam.MIARMA.users.model.User;
import com.salesianostriana.dam.MIARMA.users.model.UserRole;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserEntityRepository extends JpaRepository<User, UUID> {

    Optional<User> findFirstByEmail(String email);

    Optional<User> findById(UUID id);

    Optional<User> findByNick(String nick);

    List<User> findByRoles(UserRole roles);

    @EntityGraph("grafo-followers-user")
    List<User> findAll();


    @Query(value = """
            SELECT * FROM user u
            WHERE u.id = (SELECT user_id FROM follows
                          WHERE following_id = :followed_id)
            """, nativeQuery = true)
    List<User> findFollowers(@Param("followed_id") UUID id);

    boolean existsByNick(String nick);

    boolean existsByEmail(String email);


    Optional<User> findByFollowsContains(User user);


}
