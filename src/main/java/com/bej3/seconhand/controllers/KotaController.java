package com.bej3.seconhand.controllers;

import com.bej3.seconhand.entities.Kota;
import com.bej3.seconhand.payloads.requests.KotaRequest;
import com.bej3.seconhand.payloads.responses.WebResponse;
import com.bej3.seconhand.services.KotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kota")
public class KotaController {
    private final KotaService kotaService;

    @Autowired
    public KotaController(KotaService kotaService) {
        this.kotaService = kotaService;
    }

    @GetMapping("/getListKota")
    public WebResponse<List<Kota>> getListKota(){
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "Get List Kota",
                kotaService.getListKota()
        );
    }

    @PostMapping("/addKota")
    public WebResponse<Kota> insertKota(@RequestBody KotaRequest kotaRequest){
        Kota kota = kotaService.insertKota(kotaRequest);
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "Berhasil insert",
                kota
        );
    }

}