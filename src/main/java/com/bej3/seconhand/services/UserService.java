package com.bej3.seconhand.services;

import com.bej3.seconhand.entities.User;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.UserRequest;

public interface UserService {
    User addUser(UserRequest userRequest);
    User getUserById(int id) throws NotFoundException;

}