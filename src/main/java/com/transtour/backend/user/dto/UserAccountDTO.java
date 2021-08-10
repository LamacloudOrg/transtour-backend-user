package com.transtour.backend.user.dto;

import com.transtour.backend.user.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserAccountDTO {
    private Long dni;
    private boolean enabled;
    List<Role> roles;
}
