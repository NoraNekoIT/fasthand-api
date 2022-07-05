package com.bej3.seconhand.payloads.requests;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter

public class ChangePasswordRequest implements Serializable {
    @NotNull(message = "password lama harus diisi")
    private Integer idUser;

    private String oldPassword;
    private String newPassword;

}
