package com.bej3.seconhand.services;

import com.bej3.seconhand.entities.UserDetails;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.ChangePasswordRequest;
import com.bej3.seconhand.payloads.requests.UserLoginRequest;
import com.bej3.seconhand.payloads.requests.UserSignupRequest;
import com.bej3.seconhand.payloads.requests.UserUpdateRequest;
import com.bej3.seconhand.payloads.responses.ChangePasswordResponse;
import com.bej3.seconhand.payloads.responses.WebResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface UserService {
//    Users loginUser(LoginRequest loginRequest) throws NotFoundException;
//    Users addUser(UserRequest userRequest);

    WebResponse<String,?> authenticateUser(UserLoginRequest userLoginRequest);
    ResponseEntity<?> registerUser(UserSignupRequest signUpRequestUser);
    WebResponse<String,?> getUserById(int idUser) throws NotFoundException;
    WebResponse<String,?> updateUserDetail(UserUpdateRequest updateUserRequest)
            throws NotFoundException, IOException;
    WebResponse<String,?>  changePasswordUser(ChangePasswordRequest changePasswordRequest);
    UserDetails getGambarUser(int idUserDetail) throws NotFoundException;
}