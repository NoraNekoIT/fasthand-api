package com.bej3.seconhand.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    @Operation(description = "akses halaman tanpa security")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/buyer")
    @Operation(description = "akses halaman dengan security",
            security = @SecurityRequirement(
                    name = "bearerAuth"
            ))
    @PreAuthorize("hasRole('BUYER')")
    public String buyerAccess() {
        return "Buyer Board.";
    }

    @GetMapping("/seller")
    @Operation(description = "akses halaman dengan security",
            security = @SecurityRequirement(
                    name = "bearerAuth"
            ))
    @PreAuthorize("hasRole('SELLER')")
    public String sellerAccess() {
        return "Seller Board.";
    }
}
