package com.bej3.seconhand.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponse {

    private String name;

    private String email;

    private boolean role;

}
