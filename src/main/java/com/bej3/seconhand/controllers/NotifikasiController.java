package com.bej3.seconhand.controllers;

import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.services.NotifikasiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class NotifikasiController {

    private final NotifikasiService notifikasiService;

    @Autowired
    public NotifikasiController(NotifikasiService notifikasiService) {
        this.notifikasiService = notifikasiService;
    }

    @GetMapping("/user/{idUser}/notifikasi")
    @PreAuthorize("hasRole('BUYER')or hasRole('SELLER')")
    @Operation(description = "untuk mendapatkan notifikasi ",security = @SecurityRequirement(
            name = "bearerAuth"
    ))
    public ResponseEntity<?> getNotifikasiByIdUser(@PathVariable Integer idUser) throws NotFoundException {
        return notifikasiService.getNotifikasiByIdUser(idUser);
    }
}