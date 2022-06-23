package com.bej3.seconhand.payloads.requests;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class UserUpdateRequest {
    private MultipartFile file;
    private int idUserDetails;
    private String alamat;
    private int idKota;
    private String noHp;
//    private byte[] gambarUser;
}