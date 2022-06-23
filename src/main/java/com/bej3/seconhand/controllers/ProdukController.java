package com.bej3.seconhand.controllers;
import com.bej3.seconhand.entities.Produk;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.ProdukAddRequest;
import com.bej3.seconhand.payloads.responses.ProdukResponse;
import com.bej3.seconhand.payloads.responses.WebResponse;
import com.bej3.seconhand.services.ProdukService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/produk")
public class ProdukController {
    private final ProdukService produkService;

    @Autowired
    public ProdukController(ProdukService produkService) {
        this.produkService = produkService;
    }

    @GetMapping("/getListProduk")
    public WebResponse<Stream<ProdukResponse>> getListProduk(){
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "Get list all produk",
                produkService.getListProduk()
        );
    }

    @GetMapping("/getListProduk/{idPenjual}")
    public WebResponse<Stream<ProdukResponse>> getListProdukByPenjual(@PathVariable int idPenjual) throws NotFoundException {
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "Get list all produk by penjual",
                produkService.getListProdukByPenjual(idPenjual)
        );
    }
    @GetMapping("/getListProduk/kategori/{idKategori}")
    public WebResponse<Stream<ProdukResponse>> getListProdukByKategori(@PathVariable int idKategori) throws NotFoundException {
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "Get list all produk by Kategori",
                produkService.getListProdukByKategori(idKategori)
        );
    }

    @PostMapping("/addProduk")
    public WebResponse<Produk> insertProduk(@RequestBody ProdukAddRequest produkAddRequest) throws NotFoundException {
        Produk produk = produkService.addProduk(produkAddRequest);
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "Berhasil add Produk",
                produk
        );
    }

}