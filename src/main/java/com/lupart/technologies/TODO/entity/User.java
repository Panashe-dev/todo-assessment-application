package com.lupart.technologies.TODO.entity;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "users")
@Table(name = "Users")
public class User extends AbstractAuditingEntity  {

    private static final long serialVersionUID= -6234057529255411648L;

    private String firstName;
    @Column(
            nullable = false,
            length = 45
    )
    private String lastName;
    private String username;
    @Column(
            unique = true,
            length = 45,
            nullable = false
    )
    private String email;
    @Column(
            nullable = false
    )
    private String password;
    private boolean active;


    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private Collection<Role> roles = new ArrayList<>();


}
