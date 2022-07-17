package com.bej3.seconhand.controllers;

import com.bej3.seconhand.entities.Kategori;
import com.bej3.seconhand.payloads.responses.WebResponse;
import com.bej3.seconhand.services.KategoriService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/kategori")
public class KategoriController {
    private final KategoriService kategoriService;

    @Autowired
    public KategoriController(KategoriService kategoriService) {
        this.kategoriService = kategoriService;
    }

    @GetMapping("/all")
    @Operation(description = "untuk mendapatkan nama nama kategori")
    public WebResponse<String,?> getListKategori(){
      return kategoriService.getListKategori();
    }

}