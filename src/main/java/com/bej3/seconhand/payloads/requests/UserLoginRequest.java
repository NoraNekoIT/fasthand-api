package com.bej3.seconhand.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class UserLoginRequest {
    @Email(message = "format email salah")
    @NotBlank(message = "email tidak boleh kosong")
    private String email;
    
    @NotBlank(message = "password tidak boleh kosong")
    private String password;
}
