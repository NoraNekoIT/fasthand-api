package com.bej3.seconhand.services.impls;

import com.bej3.seconhand.entities.*;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.UserLoginRequest;
import com.bej3.seconhand.payloads.requests.UserSignupRequest;
import com.bej3.seconhand.payloads.requests.UserUpdateRequest;
import com.bej3.seconhand.payloads.responses.*;
import com.bej3.seconhand.repositories.KotaRepository;
import com.bej3.seconhand.repositories.RoleRepository;
import com.bej3.seconhand.repositories.UserDetailRepository;
import com.bej3.seconhand.repositories.UserRepository;
import com.bej3.seconhand.securities.jwt.JwtUtils;
import com.bej3.seconhand.services.UserService;
import com.bej3.seconhand.utils.HerokuUrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final KotaRepository kotaRepository;
    private final RoleRepository roleRepository;
    private final HerokuUrlUtil herokuUrlUtil;
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
                           RoleRepository roleRepository,
                           HerokuUrlUtil herokuUrlUtil
    ) {
        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
        this.kotaRepository = kotaRepository;
        this.roleRepository = roleRepository;
        this.herokuUrlUtil = herokuUrlUtil;
    }

//    @Override
//    public Users loginUser(LoginRequest loginRequest) throws NotFoundException {
//        return userRepository.login(loginRequest.getEmail(),
//                loginRequest.getPassword()).orElseThrow(NotFoundException::new);
//    }
//
//    @Override
//    public Users addUser(UserRequest userRequest) {
//        Users user =  new Users(
//                userRequest.getName(),
//                userRequest.getEmail(),
//                userRequest.getPassword()
////                false
//        );
//        UserDetails userDetails = new UserDetails(
//                "",
//                "",
//                null
//        );
//        user.setUserDetail(userDetails);
//        userDetails.setUsers(user);
//        return userRepository.save(user);
//    }

    @Override
    public WebResponse<String, ?> authenticateUser(UserLoginRequest userLoginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(),
                        userLoginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "OK",
                "Berhasil Login",
                new UserLoginResponse(
                        jwt,
                        userDetails.getId(),
                        userDetails.getEmail()
                )
        );
    }

    @Override
    public ResponseEntity<?> registerUser(UserSignupRequest signUpRequestUser) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequestUser.getEmail()))) {
            return ResponseEntity.badRequest().body(
                    new WebResponse<>(
                            HttpStatus.BAD_REQUEST.value(),
                            "BAD REQUEST",
                            "Email Sudah digunakan coba email lain",
                            ""
                    )
            );
        }
        Users user = new Users(signUpRequestUser.getName(),
                signUpRequestUser.getEmail(),
                encoder.encode(signUpRequestUser.getPassword()));

        UserDetails userDetails = new UserDetails(
                "",
                "",
                null
        );
        user.setUserDetail(userDetails);
        userDetails.setUsers(user);

        Role buyerRole = roleRepository.findByNamaRole(ERole.ROLE_BUYER).
                orElseThrow(() -> new RuntimeException("Error: Role is not found. Tambahkan role dulu di database"));
        Role sellerRole = roleRepository.findByNamaRole(ERole.ROLE_SELLER).orElseThrow(
                ()-> new RuntimeException("Error: Role is not found. Tambahkan role dulu di database"));
        Set<Role> roles = new HashSet<>();
        roles.add(buyerRole);
        roles.add(sellerRole);
        user.setRoles(roles);

        userRepository.save(user);
        return ResponseEntity.ok().body(
        new WebResponse<>(
                HttpStatus.OK.value(),
                "OK",
                "Berhasil Daftar User",
                new UserDaftarResponse(
                        user.getIdUser(),
                        user.getName(),
                        user.getEmail())
        ));
    }

    @Override
    public WebResponse<String, ?> getUserById(int idUser) throws NotFoundException {
        Users user = userRepository.findById(idUser).orElseThrow(() -> new NotFoundException("id user tidak ditemukan"));
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "OK",
                "Detail user",
                new UserResponse(user.getIdUser(), user.getName(), user.getEmail(),
                        new UserDetailResponse(
                                user.getUserDetail().getIdUserDetails(),
                                user.getUserDetail().getAlamat(),
                                user.getUserDetail().getKota(),
                                user.getUserDetail().getNoHp(),
                                convertGambarUserToLinkGambarUser(user.getUserDetail()))));
    }

    @Override
    public WebResponse<String, ?> updateUserDetail(UserUpdateRequest updateUserRequest) throws NotFoundException, IOException {
        UserDetails userDetails = userDetailRepository.findById(updateUserRequest.getIdUserDetails()).
                orElseThrow(() -> new NotFoundException("id user Detail Not Found"));

        if (updateUserRequest.getAlamat() != null) {
            userDetails.setAlamat(updateUserRequest.getAlamat());
        }
        if (updateUserRequest.getIdKota() != null) {
            Kota kota = kotaRepository.findById(updateUserRequest.getIdKota()).
                    orElseThrow(() -> new NotFoundException("id Kota tidak ditemukan cari kota lain"));
            userDetails.setKota(kota);
            userDetails.setNoHp(updateUserRequest.getNoHp());
        }
        if (!updateUserRequest.getMultipartFile().isEmpty() ||
                updateUserRequest.getMultipartFile() != null ||
                updateUserRequest.getMultipartFile().getSize() >= 1) {
            userDetails.setTypeGambarUser(updateUserRequest.getMultipartFile().getContentType());
            userDetails.setGambarUser(updateUserRequest.getMultipartFile().getBytes());
        }

        userDetailRepository.save(userDetails);
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "OK",
                "Berhasil update user",
                new UserDetailResponse(
                        userDetails.getIdUserDetails(),
                        userDetails.getAlamat(),
                        userDetails.getKota(),
                        userDetails.getNoHp(),
                        convertGambarUserToLinkGambarUser(userDetails)
                ));
    }

    @Override
    public UserDetails getGambarUser(int idUserDetail) throws NotFoundException {
        return userDetailRepository.findById(idUserDetail).orElseThrow(
                () -> new NotFoundException("id detail user tidak ada"));
    }
    private UserGambarLinkResponse convertGambarUserToLinkGambarUser(UserDetails userDetails){
        if (userDetails.getGambarUser() != null ) {
            return new UserGambarLinkResponse(
                    herokuUrlUtil.getUrlApi() + "api/user/" + userDetails.getIdUserDetails() + "/detail/gambar/"
            );
        } else {
            return new UserGambarLinkResponse("");
        }
    }
}