package com.bej3.seconhand.payloads.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequest {

    private String name;

    private String email;

    private String password;

    @JsonIgnore
    private boolean role;
}
