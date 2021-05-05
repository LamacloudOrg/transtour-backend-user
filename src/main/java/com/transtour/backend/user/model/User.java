package com.transtour.backend.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_name",unique = true)
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    //@NotEmpty(message = "Please provide your first name")
    private String firstName;
    @Column(name = "last_name")
    //@NotEmpty(message = "Please provide your last name")
    private String lastName;
    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    List<Role> roles;


}
