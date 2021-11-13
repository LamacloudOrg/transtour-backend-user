package com.transtour.backend.user.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="cars")
@Data
public class Car {

    @Id
    @Column(name = "patent",unique = true)
    private String id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "colour")
    private String color;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "driver", nullable = false,updatable = false)
    private User driver;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "driver", nullable = false, insertable = false,updatable = false)
    private User owner;
}
