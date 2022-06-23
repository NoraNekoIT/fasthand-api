package com.bej3.seconhand.services.impls;

import com.bej3.seconhand.entities.Kota;
import com.bej3.seconhand.entities.Users;
import com.bej3.seconhand.entities.UserDetails;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.LoginRequest;
import com.bej3.seconhand.payloads.requests.UserDetailRequest;
import com.bej3.seconhand.payloads.requests.UserUpdateRequest;
import com.bej3.seconhand.payloads.requests.UserRequest;
import com.bej3.seconhand.repositories.KotaRepository;
import com.bej3.seconhand.repositories.UserDetailRepository;
import com.bej3.seconhand.repositories.UserRepository;
import com.bej3.seconhand.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.io.IOException;


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
        return userRepository.login(loginRequest.getEmail(),
                loginRequest.getPassword()).orElseThrow(NotFoundException::new);
    }

    @Override
    public Users addUser(UserRequest userRequest) {
        Users user =  new Users(
                userRequest.getName(),
                userRequest.getEmail(),
                userRequest.getPassword()
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
    public UserDetailRequest findUserDetailLogin(){
        SecurityContext ctx = SecurityContextHolder.getContext();
        UserDetailRequest userDetailRequest = new UserDetailRequest();
        userDetailRequest.setEmail(ctx.getAuthentication().getName());
        return userDetailRequest;
    }


    @Override
    public UserDetails updateUserDetail(UserUpdateRequest updateUserRequest) throws NotFoundException, IOException {
        UserDetails userDetails = userDetailRepository.findById(updateUserRequest.getIdUserDetails()).orElseThrow(NotFoundException::new);
        userDetails.setAlamat(updateUserRequest.getAlamat());
        Kota kota = kotaRepository.findById(updateUserRequest.getIdKota()).orElseThrow(NotFoundException::new);
        userDetails.setKota(kota);
        userDetails.setGambarUser(updateUserRequest.
                getFile().getBytes());
        return userDetails;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("invalid email"));
    }
}