package com.transtour.backend.user.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DriverDTO {
    private Long dni;
    private String fullName;
    private List<CarDTO> car = new ArrayList<>();

}
