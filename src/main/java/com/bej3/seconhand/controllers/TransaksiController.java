package com.bej3.seconhand.controllers;

import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.PenawaranCreateRequest;
import com.bej3.seconhand.payloads.requests.PenawaranStatusRequest;
import com.bej3.seconhand.payloads.requests.TransaksiStatusRequest;
import com.bej3.seconhand.services.TransaksiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TransaksiController {
    private final TransaksiService  transaksiService;
    @Autowired
    public TransaksiController(TransaksiService transaksiService) {
        this.transaksiService = transaksiService;
    }
    @PreAuthorize("hasRole('BUYER')")
    @PostMapping("/penawaran")
    @Operation(description = "untuk bikin penawaran",
            security = @SecurityRequirement(
                    name = "bearerAuth"
            )
    )
    public ResponseEntity<?> addPenawaran(@Valid @RequestBody PenawaranCreateRequest penawaranCreateRequest) throws NotFoundException {
        return transaksiService.addPenawaran(penawaranCreateRequest);
    }

    @PreAuthorize("hasRole('SELLER')")
    @Operation(description = "untuk mengubah state penawaran",
            security = @SecurityRequirement(
                    name = "bearerAuth"
            )
    )
    @PatchMapping("/penawaran")
    public ResponseEntity<?> changeStatePenawaran(@Valid @RequestBody
                                                              PenawaranStatusRequest penawaranStatusRequest) throws NotFoundException {
        return transaksiService.changeStatePenawaran(penawaranStatusRequest);
    }

    @PreAuthorize("hasRole('SELLER')")
    @Operation(description = "untuk mengubah state transaksi",
            security = @SecurityRequirement(
                    name = "bearerAuth"
            )
    )
    @PatchMapping("/transaksi")
    public ResponseEntity<?> changeStateTransaksi(@Valid @RequestBody
                                                              TransaksiStatusRequest transaksiStatusRequest) throws NotFoundException {
        return transaksiService.changeStateTransaksi(transaksiStatusRequest);
    }
}