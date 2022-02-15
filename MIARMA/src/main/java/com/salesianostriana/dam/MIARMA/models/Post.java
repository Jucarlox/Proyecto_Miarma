package com.salesianostriana.dam.MIARMA.models;

import com.salesianostriana.dam.MIARMA.users.model.User;
import lombok.*;

import javax.persistence.*;

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

    @ManyToOne
    private User user;




}
