package com.bej3.seconhand.payloads.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
    private int idUserDetails;
    private String alamat;
    private int idKota;
    private String noHp;
    private byte[] gambarUser;
}
