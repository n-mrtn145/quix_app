package com.quix.quix.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDataDto {
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty
    private String createdAt;
}
