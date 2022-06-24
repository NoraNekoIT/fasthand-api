package com.bej3.seconhand.services;

import com.bej3.seconhand.entities.Users;
import com.bej3.seconhand.entities.UserDetails;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.LoginRequest;
import com.bej3.seconhand.payloads.requests.SignupRequest;
import com.bej3.seconhand.payloads.requests.UserUpdateRequest;
import com.bej3.seconhand.payloads.requests.UserRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    Users loginUser(LoginRequest loginRequest) throws NotFoundException;
    Users addUser(UserRequest userRequest);

    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    ResponseEntity<?> registerUser(SignupRequest signUpRequest);
    Users getUserById(int id) throws NotFoundException;
    UserDetails updateUserDetail(UserUpdateRequest updateUserRequest) throws NotFoundException;
}