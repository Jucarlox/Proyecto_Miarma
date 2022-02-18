package com.salesianostriana.dam.MIARMA.models;

import com.salesianostriana.dam.MIARMA.users.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;


@NamedEntityGraph(
        name = "grafo-peticion-user", attributeNodes = {
        @NamedAttributeNode("user"),
}
)


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Peticion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @PreRemove
    public void borrarDestinatarios() {
        user.setPeticionList(null);
    }


}
