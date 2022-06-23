package com.bej3.seconhand.controllers;

import com.bej3.seconhand.entities.Users;
import com.bej3.seconhand.entities.UserDetails;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.LoginRequest;
import com.bej3.seconhand.payloads.requests.UserUpdateRequest;
import com.bej3.seconhand.payloads.requests.UserRequest;
import com.bej3.seconhand.payloads.responses.WebResponse;
import com.bej3.seconhand.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/daftar")
    public WebResponse<Users> daftarUser(@RequestBody UserRequest userRequest) {
        Users user = userService.addUser(userRequest);
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "BERHASIL INSERT",
                user
        );
    }

    @GetMapping("/{id}")
    public WebResponse<Users> getUserById(@PathVariable("id") int id) throws NotFoundException {
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "GET ID",
                userService.getUserById(id)
        );
    }

    @PostMapping(value = "/update",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public WebResponse<?> updateUserDetail(
            @ModelAttribute UserUpdateRequest updateUserRequest
    ) throws NotFoundException, IOException {
//        UserDetails userDetails = userService.updateUserDetail(updateUserRequest);
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "BERHASIL UPDATE",
                updateUserRequest.getIdKota() +
                        updateUserRequest.getIdUserDetails() +
                        updateUserRequest.getAlamat() +
                        updateUserRequest.getNoHp() +
                        Arrays.toString(updateUserRequest.getFile().getBytes())
        );
    }

    @PostMapping("/login")
    public WebResponse<Users> loginDaftar(@RequestBody LoginRequest loginRequest) throws NotFoundException {
        Users user = userService.loginUser(loginRequest);
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "Berhasil Login",
                user
        );
    }

}