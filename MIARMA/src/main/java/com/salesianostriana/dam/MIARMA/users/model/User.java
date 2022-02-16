package com.salesianostriana.dam.MIARMA.users.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesianostriana.dam.MIARMA.Dto.Post.GetPostDto;

import com.salesianostriana.dam.MIARMA.models.Estado;
import com.salesianostriana.dam.MIARMA.models.Peticion;
import com.salesianostriana.dam.MIARMA.models.Post;
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
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;




@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
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
    private String nick;
    private Date fechaNacimiento;

    @Enumerated(EnumType.STRING)
    private UserRole roles;
    private String avatar;

    @Enumerated(EnumType.STRING)
    private Estado privacity;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> postList;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "follows",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id"))
    private List<User> follows = new ArrayList<>();


    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Peticion> peticionList = new ArrayList<>();




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




    public void addPeticion(Peticion p) {
        if (this.getPeticionList() == null)
            this.setPeticionList(new ArrayList<>());
        this.getPeticionList().add(p);
    }

    public void removePeticion(Peticion p) {
        this.getPeticionList().remove(p);
        p = null;

    }

    public void addFollower(User u) {
        this.follows = List.of(u);
        u.getFollows().add(this);

    }

    public void removeFollower(User u) {
        this.getFollows().remove(u);
        u = null;

    }


}
