package com.quix.quix.security;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class User {

    @JsonProperty("id")
    private UUID id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("display-name")
    private String username;
    @JsonProperty("createdAt")
    private Instant createdAt;
    @JsonProperty("password")
    private String password;


}