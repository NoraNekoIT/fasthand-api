package com.bej3.seconhand.services.impls;

import com.bej3.seconhand.entities.*;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.LoginRequest;
import com.bej3.seconhand.payloads.requests.SignupRequest;
import com.bej3.seconhand.payloads.requests.UserUpdateRequest;
import com.bej3.seconhand.payloads.requests.UserRequest;
import com.bej3.seconhand.payloads.responses.JwtResponse;
import com.bej3.seconhand.payloads.responses.MessageResponse;
import com.bej3.seconhand.repositories.KotaRepository;
import com.bej3.seconhand.repositories.RoleRepository;
import com.bej3.seconhand.repositories.UserDetailRepository;
import com.bej3.seconhand.repositories.UserRepository;
import com.bej3.seconhand.security.jwt.JwtUtils;
import com.bej3.seconhand.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final KotaRepository kotaRepository;
    private final RoleRepository roleRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserDetailRepository userDetailRepository,
                           KotaRepository kotaRepository,
                           RoleRepository roleRepository
                           ) {
        this.userRepository = userRepository;
        this.userDetailRepository =userDetailRepository;
        this.kotaRepository = kotaRepository;
        this.roleRepository = roleRepository;
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
//                false
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
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail()
        ));
    }

    @Override
    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        Users user = new Users(signUpRequest.getName(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        UserDetails userDetails = new UserDetails(
                "",
                "",
                null
        );
        user.setUserDetail(userDetails);
        userDetails.setUsers(user);

        Role buyerRole = roleRepository.findByNamaRole(ERole.ROLE_BUYER).
                orElseThrow(() -> new RuntimeException("Error: Role is not found. Tambahkan role dulu di database"));
        Set<Role> roles = new HashSet<>();
        roles.add(buyerRole);
        user.setRoles(roles);

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
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