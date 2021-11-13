package com.transtour.backend.user.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="licences")
@Data
public class Licence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lcence_id",unique = true)
    private Long id;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "category")
    private String category;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "driver", nullable = false)
    private User driver;
}
