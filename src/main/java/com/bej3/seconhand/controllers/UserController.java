package com.bej3.seconhand.controllers;

import com.bej3.seconhand.entities.User;
import com.bej3.seconhand.errors.NotFoundException;
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

    @PostMapping
    public WebResponse<User> daftarUser(@RequestBody UserRequest userRequest){
        User user = userService.addUser(userRequest);
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "BERHASIL INSERT",
                user
        );
    }

    @GetMapping("/{id}")
    public WebResponse<User> getUserById(@PathVariable("id")  int id) throws NotFoundException {
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "GET ID",
                userService.getUserById(id)
        );
    }

}