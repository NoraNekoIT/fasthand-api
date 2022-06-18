package com.bej3.seconhand.controllers;

import com.bej3.seconhand.entities.Kategori;
import com.bej3.seconhand.payloads.responses.WebResponse;
import com.bej3.seconhand.services.KategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/kategori")
public class KategoriController {
    private final KategoriService kategoriService;

    @Autowired
    public KategoriController(KategoriService kategoriService) {
        this.kategoriService = kategoriService;
    }

    @GetMapping
    public WebResponse<List<Kategori>> getListKategori(){
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "Get List Kategori",
                kategoriService.getListKategori()
        );
    }
}
