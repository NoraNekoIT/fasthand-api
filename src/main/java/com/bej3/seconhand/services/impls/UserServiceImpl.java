package com.bej3.seconhand.services.impls;

import com.bej3.seconhand.entities.Kota;
import com.bej3.seconhand.entities.User;
import com.bej3.seconhand.entities.UserDetails;
import com.bej3.seconhand.errors.NotFoundException;
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
    public User addUser(UserRequest userRequest) {
        User user =  new User(
                userRequest.getName(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                false
        );
        UserDetails userDetails = new UserDetails(
                "","",null
        );
        user.setUserDetail(userDetails);
        userDetails.setUser(user);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(int id) throws NotFoundException {
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
