package com.jobtang.sourcecompany.api.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequestDto {
    private String username;
    private String password;
}
