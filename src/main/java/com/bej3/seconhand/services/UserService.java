package com.bej3.seconhand.services;

import com.bej3.seconhand.entities.Users;
import com.bej3.seconhand.entities.UserDetails;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.LoginRequest;
import com.bej3.seconhand.payloads.requests.UserUpdateRequest;
import com.bej3.seconhand.payloads.requests.UserRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    Users loginUser(LoginRequest loginRequest) throws NotFoundException;
    Users addUser(UserRequest userRequest);
    Users getUserById(int id) throws NotFoundException;
    UserDetails updateUserDetail(UserUpdateRequest updateUserRequest) throws NotFoundException, IOException;
}