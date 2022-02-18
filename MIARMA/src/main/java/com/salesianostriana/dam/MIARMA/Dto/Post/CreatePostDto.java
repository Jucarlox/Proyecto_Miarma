package com.salesianostriana.dam.MIARMA.Dto.Post;

import com.salesianostriana.dam.MIARMA.models.Estado;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePostDto {

    @NotEmpty
    private String title;
    @NotEmpty
    private String descripcion;
    private String fileOriginal;
    private String fileScale;
    private boolean privacity;

}

