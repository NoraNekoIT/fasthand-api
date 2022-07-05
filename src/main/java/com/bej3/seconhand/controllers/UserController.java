package com.bej3.seconhand.controllers;

import com.bej3.seconhand.entities.GambarProduk;
import com.bej3.seconhand.entities.UserDetails;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.ChangePasswordRequest;
import com.bej3.seconhand.payloads.requests.UserUpdateRequest;
import com.bej3.seconhand.payloads.responses.WebResponse;
import com.bej3.seconhand.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/daftar")
//    public WebResponse<Users> daftarUser(@RequestBody UserRequest userRequest){
//        Users user = userService.addUser(userRequest);
//        return new WebResponse<>(
//                HttpStatus.OK.value(),
//                "BERHASIL INSERT",
//                user
//        );
//    }

    @GetMapping("/{idUser}/detail")
    @PreAuthorize("hasRole('BUYER')or hasRole('SELLER')")
    @Operation(description = "untuk mendapatkan detail user", security = @SecurityRequirement(
            name = "bearerAuth"
    ))
    public WebResponse<String, ?> getUserById(@PathVariable("idUser") int id) throws NotFoundException {
        return userService.getUserById(id);

    }

    @RequestMapping(value = "/update",
            method = RequestMethod.PUT,
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(description = "untuk update user", security = @SecurityRequirement(
            name = "bearerAuth"
    ))
    @PreAuthorize("hasRole('BUYER')or hasRole('SELLER')")
    public WebResponse<String, ?> updateUserDetail(@Valid @ModelAttribute UserUpdateRequest updateUserRequest)
            throws NotFoundException, IOException {
        return userService.updateUserDetail(updateUserRequest);
    }

    @GetMapping("/{idUserDetail}/detail/gambar")
    @Operation(description = "untuk mendapatkan gambar user")
    public ResponseEntity<byte[]> getGambarUser(@PathVariable int idUserDetail) throws NotFoundException {
        UserDetails userDetails = userService.getGambarUser(idUserDetail);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(userDetails.getTypeGambarUser()))
                .body(userDetails.getGambarUser());
    }

    @Operation(description = "untuk mengganti password", security = @SecurityRequirement(
            name = "bearerAuth"
    ))
    @RequestMapping(value = "/change-password",
            method = RequestMethod.PUT)
    @PreAuthorize("hasRole('BUYER')or hasRole('SELLER')")
    public ResponseEntity<?> ChangePasswordUser(@Valid @RequestBody ChangePasswordRequest changePasswordRequest)
            throws NotFoundException, IOException {
        return userService.ChangePasswordUser(changePasswordRequest);
    }
//    @PostMapping("/login")
//    public WebResponse<Users> loginDaftar(@RequestBody LoginRequest loginRequest) throws NotFoundException {
//       Users user = userService.loginUser(loginRequest);
//        return new WebResponse<>(
//                HttpStatus.OK.value(),
//                "Berhasil Login",
//                user
//        );
//    }
}