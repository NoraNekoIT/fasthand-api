package com.bej3.seconhand.services;

import com.bej3.seconhand.entities.User;
import com.bej3.seconhand.entities.UserDetails;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.LoginRequest;
import com.bej3.seconhand.payloads.requests.UserUpdateRequest;
import com.bej3.seconhand.payloads.requests.UserRequest;

import java.util.Optional;

public interface UserService {
    User loginUser(LoginRequest loginRequest) throws NotFoundException;
    User addUser(UserRequest userRequest);
    User getUserById(int id) throws NotFoundException;
    UserDetails updateUserDetail(UserUpdateRequest updateUserRequest) throws NotFoundException;
}