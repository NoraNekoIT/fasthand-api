package com.bej3.seconhand.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupRequest {

    @NotBlank(message = "name tidak boleh kosong")
    private String name;

    @Email(message = "format email salah")
    @NotBlank(message = "email tidak boleh kosong")
    private String email;

    @Size(min = 6, message = "password harus memiliki 6 karakter")
    @NotBlank(message = "password tidak boleh kosong")
    private String password;
}
