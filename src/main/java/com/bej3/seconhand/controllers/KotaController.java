package com.bej3.seconhand.controllers;

import com.bej3.seconhand.entities.Kota;
import com.bej3.seconhand.payloads.requests.KotaRequest;
import com.bej3.seconhand.payloads.responses.WebResponse;
import com.bej3.seconhand.services.KotaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/kota")
public class KotaController {
    private final KotaService kotaService;

    @Autowired
    public KotaController(KotaService kotaService) {
        this.kotaService = kotaService;
    }

    @GetMapping("/all/paging")
    @Operation(description = "mendapatkan semua nama kota, " +
            "searchNamaKota sebagai searching berdasarkan nama kota, "+
            "pageNo sebagai no dari halaman, " +
            "pageSize sebagai jumlah kota yang ingin ditampilkan, " +
            "sortBy sorting berdasarkan key Json misal bisa diisi idKota atau namaKota"
    )
    public WebResponse<String, ?> getListKota(
            @RequestParam(required = false) String searchNamaKota,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "idKota") String sortBy
    ) {
        return kotaService.getListKota(searchNamaKota,pageNo, pageSize, sortBy);
    }

    @GetMapping("/all")
    @Operation(description = "Mendapatkan Semua Kota tanpa Pagination")
    public ResponseEntity<?> getListKotaWithoutPagination(){
        return kotaService.getListKotaWithoutPagination();
    }

//    @PostMapping("/addKota")
//    public WebResponse<Kota> insertKota(@RequestBody KotaRequest kotaRequest){
//        Kota kota = kotaService.insertKota(kotaRequest);
//        return new WebResponse<>(
//                HttpStatus.OK.value(),
//                "Berhasil insert",
//                kota
//        );
//    }

}