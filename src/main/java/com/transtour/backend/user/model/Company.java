package com.transtour.backend.user.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
@Data
public class Company {

    @Id
    @Column(name = "company_id", unique = true)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "cuit")
    private String cuit;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "whiting_time_amount")
    private String whitingTimeAmount;
    @Column(name = "disposition_time_amount")
    private String dispositionTimeAmount;
}
