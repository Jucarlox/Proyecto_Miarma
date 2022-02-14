package com.salesianostriana.dam.realstatev2.users.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesianostriana.dam.realstatev2.model.Inmobiliaria;
import com.salesianostriana.dam.realstatev2.model.Interesa;
import com.salesianostriana.dam.realstatev2.model.Vivienda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;



@NamedEntityGraph(
        name = User.USER_CON_VIVIENDA,
        attributeNodes = {
                @NamedAttributeNode("viviendas")
        }
)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    public static final String USER_CON_VIVIENDA= "grafo-user-con-vivienda";
    @Id @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NaturalId
    @Column(unique = true, updatable = false)

    private String email;
    private String password;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;

    @Enumerated(EnumType.STRING)
    private UserRole roles;
    private String avatar;

    @ManyToOne
    @JoinColumn(name = "inmobiliaria_id", foreignKey = @ForeignKey(name = "FK_USER_INMOBILIARIA"), nullable = true)
    private Inmobiliaria inmobiliaria;

    @Builder.Default
    @OneToMany(mappedBy = "propietario",fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    @JsonIgnore
    private List<Vivienda> viviendas=new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "interesado", fetch = FetchType.EAGER)
    private List<Interesa> interesas = new ArrayList<>();

    @PreRemove
    public void preRemove(){
        interesas.forEach(v -> v.setInteresado(null));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + roles.name()));
    }




    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public void addInmobiliaria(Inmobiliaria i) {
        this.inmobiliaria = i;
        i.getGestores().add(this);
    }

    public void removeInmobiliaria(Inmobiliaria i) {
        i.getGestores().remove(this);
        this.inmobiliaria = null;
    }


}
