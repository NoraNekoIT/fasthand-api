package com.bej3.seconhand.payloads.responses;

import com.bej3.seconhand.entities.Kota;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDetailResponse {
    private Integer idUserDetails;
    private String alamat;
    private Kota kota;
    private String noHp;
    private UserGambarLinkResponse gambarUser;
}