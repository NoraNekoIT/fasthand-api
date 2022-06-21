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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/daftar")
    public WebResponse<Users> daftarUser(@RequestBody UserRequest userRequest){
        Users user = userService.addUser(userRequest);
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "BERHASIL INSERT",
                user
        );
    }

    @GetMapping("/{id}")
    public WebResponse<Users> getUserById(@PathVariable("id")  int id) throws NotFoundException {
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "GET ID",
                userService.getUserById(id)
        );
    }

    @PostMapping("/update")
    public WebResponse<UserDetails> updateUserDetail(@RequestBody UserUpdateRequest updateUserRequest) throws NotFoundException {
        UserDetails userDetails = userService.updateUserDetail(updateUserRequest);
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "BERHASIL UPDATE",
                userDetails
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