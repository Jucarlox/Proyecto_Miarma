package com.salesianostriana.dam.MIARMA.Dto.Post;

import com.salesianostriana.dam.MIARMA.models.Estado;
import com.salesianostriana.dam.MIARMA.models.Post;
import com.salesianostriana.dam.MIARMA.users.dto.GetUserDto3;
import com.salesianostriana.dam.MIARMA.users.model.User;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPostDto {

    private Long id;
    private String title;
    private String descripcion;
    private String fileScale;
    private Estado privacity;
    private GetUserDto3 user;



}
