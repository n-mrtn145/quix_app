package com.quix.quix.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class UserDto {
private UUID id;
@JsonProperty("email")
private String email;
@JsonProperty("password")
private String password;
@JsonProperty("data")
    private UserDataDto data;
}

