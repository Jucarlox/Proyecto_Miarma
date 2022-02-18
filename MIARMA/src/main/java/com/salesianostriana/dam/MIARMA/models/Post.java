package com.salesianostriana.dam.MIARMA.models;

import com.salesianostriana.dam.MIARMA.users.model.User;
import lombok.*;

import javax.persistence.*;

@NamedEntityGraph(
        name = "grafo-post-user",
        attributeNodes = {
                @NamedAttributeNode("user")
        }
)

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    @Lob
    private String descripcion;

    private String fileOriginal;

    private String fileScale;

    @Enumerated(EnumType.STRING)
    private Estado privacity;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


}
