package com.bej3.seconhand.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDaftarResponse {
    private Integer idUser;
    private String name;
    private String email;
}