package com.bej3.seconhand.payloads.responses;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

    private int idUser;
    private String email;
    private String type = "Bearer";
    private String accessToken;

    public JwtResponse(String token, int id, String email) {
        this.accessToken = token;
        this.idUser = id;
        this.email = email;
    }
}