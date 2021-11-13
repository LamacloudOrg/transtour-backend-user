package com.transtour.backend.user.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    @Column(name = "dni",unique = true)
    private Long dni;
    @Column(name = "password")
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "phone")
    private String phone;
    @Column(name = "mobile_phone")
    private String mobilePhone;
    @Column(name = "email")
    private String email;
    @Column(name = "user_role")
    private String role;


    @OneToOne(mappedBy = "driver", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Car car;

    @OneToOne(mappedBy = "driver", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Licence licence;

}
