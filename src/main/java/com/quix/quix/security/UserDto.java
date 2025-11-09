package com.quix.quix.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class UserDto {
private UUID id;
@JsonProperty("username")
private String username;
@JsonProperty("password")
private String password;

}

