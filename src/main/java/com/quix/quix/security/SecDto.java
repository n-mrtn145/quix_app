package com.quix.quix.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecDto {
    private String jwtToken;
    private String refreshToken;

}
