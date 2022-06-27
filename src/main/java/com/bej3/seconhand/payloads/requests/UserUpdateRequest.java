package com.bej3.seconhand.payloads.requests;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class UserUpdateRequest implements Serializable {
    @NotNull(message = "id user null isi dulu")
    private Integer idUserDetails;
    private String alamat;
    private Integer idKota;
    private String noHp;

    @NotNull
    transient MultipartFile multipartFile = null;
}
