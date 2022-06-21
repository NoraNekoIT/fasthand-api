package com.bej3.seconhand.services.impls;

import com.bej3.seconhand.entities.Kota;
import com.bej3.seconhand.entities.Users;
import com.bej3.seconhand.entities.UserDetails;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.LoginRequest;
import com.bej3.seconhand.payloads.requests.UserUpdateRequest;
import com.bej3.seconhand.payloads.requests.UserRequest;
import com.bej3.seconhand.repositories.KotaRepository;
import com.bej3.seconhand.repositories.UserDetailRepository;
import com.bej3.seconhand.repositories.UserRepository;
import com.bej3.seconhand.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final KotaRepository kotaRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserDetailRepository userDetailRepository,
                           KotaRepository kotaRepository
                           ) {
        this.userRepository = userRepository;
        this.userDetailRepository =userDetailRepository;
        this.kotaRepository = kotaRepository;
    }

    @Override
    public Users loginUser(LoginRequest loginRequest) throws NotFoundException {
        Users user =userRepository.login(loginRequest.getEmail(),
                loginRequest.getPassword()).orElseThrow(NotFoundException::new);
        return user;
    }

    @Override
    public Users addUser(UserRequest userRequest) {
        Users user =  new Users(
                userRequest.getName(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                false
        );
        UserDetails userDetails = new UserDetails(
                "",
                "",
                null
        );
        user.setUserDetail(userDetails);
        userDetails.setUsers(user);
        return userRepository.save(user);
    }

    @Override
    public Users getUserById(int id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public UserDetails updateUserDetail(UserUpdateRequest updateUserRequest) throws NotFoundException {
        UserDetails userDetails = userDetailRepository.findById(updateUserRequest.getIdUserDetails()).orElseThrow(NotFoundException::new);
        userDetails.setAlamat(updateUserRequest.getAlamat());
        Kota kota = kotaRepository.findById(updateUserRequest.getIdKota()).orElseThrow(NotFoundException::new);
        userDetails.setKota(kota);
        return userDetails;
    }

}