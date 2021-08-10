package com.transtour.backend.user.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private Long dni;
    private String password;
}
