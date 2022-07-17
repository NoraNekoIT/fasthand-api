package com.bej3.seconhand.payloads.responses;

import com.bej3.seconhand.entities.Kota;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponse {
    private Integer idUser;
    private String name;
    private String email;
    private String alamat;
    private Kota kota;
    private String noHp;
    private UserGambarLinkResponse gambarUser;
//    private UserDetailResponse userDetails;
}
