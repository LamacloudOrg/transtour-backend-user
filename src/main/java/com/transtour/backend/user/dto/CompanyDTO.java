package com.transtour.backend.user.dto;

import lombok.Data;

@Data
public class CompanyDTO {

    private Long id;
    private String fullName;
    private String nickName;
    private String cuit;
    private String email;
    private String phone;
    private Double whitingTimeAmount;
    private Double dispositionTimeAmount;
}
