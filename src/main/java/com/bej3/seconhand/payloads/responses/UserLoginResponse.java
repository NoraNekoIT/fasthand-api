package com.bej3.seconhand.payloads.responses;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginResponse {
    private Integer idUser;
    private String email;
    private String type = "Bearer";
    private String accessToken;

    public UserLoginResponse(String token, int id, String email) {
        this.accessToken = token;
        this.idUser = id;
        this.email = email;
    }
}