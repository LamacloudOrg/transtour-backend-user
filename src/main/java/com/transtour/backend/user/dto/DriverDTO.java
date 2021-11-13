package com.transtour.backend.user.dto;

import lombok.Data;

@Data
public class DriverDTO {
    private Long dni;
    private String fullName;
    private CarDTO car;

}
