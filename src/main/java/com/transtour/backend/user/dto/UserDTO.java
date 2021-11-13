package com.transtour.backend.user.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class UserDTO {
    private Long dni;
    private String fullName;
    private String phone;
    private String mobilePhone;
    private String email;
}
