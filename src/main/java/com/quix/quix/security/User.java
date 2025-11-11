package com.quix.quix.security;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class User {

    private UUID id;
    private String email;
    private String role;
    private Instant createdAt;

}