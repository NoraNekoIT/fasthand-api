package com.bej3.seconhand.controllers;
import com.bej3.seconhand.payloads.requests.UserLoginRequest;
import com.bej3.seconhand.payloads.requests.UserSignupRequest;
import com.bej3.seconhand.payloads.responses.WebResponse;
import com.bej3.seconhand.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    public final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;

    }

    @Operation(description = "login user")
    @PostMapping("/signin")
    public WebResponse<String,?> authenticateUser(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        return userService.authenticateUser(userLoginRequest);
    }

    @Operation(description = "daftar user")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserSignupRequest signUpRequestUser) {
       return userService.registerUser(signUpRequestUser);
    }
}