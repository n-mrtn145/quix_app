package com.quix.quix.security;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDataDto {
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty
    private String createdAt;
}
