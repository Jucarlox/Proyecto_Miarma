package com.salesianostriana.dam.MIARMA.users.dto;

import com.salesianostriana.dam.MIARMA.Dto.Post.GetPostDto;
import com.salesianostriana.dam.MIARMA.models.Estado;
import com.salesianostriana.dam.MIARMA.models.Post;
import com.salesianostriana.dam.MIARMA.users.model.UserRole;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserDto {

    private UUID id;
    private String nick;
    private String email;
    private LocalDate fechaNacimiento;
    private String avatar;
    private Estado estado;
    private List<GetPostDto> postList;


}