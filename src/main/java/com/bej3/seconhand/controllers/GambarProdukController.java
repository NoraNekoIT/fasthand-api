package com.bej3.seconhand.controllers;
import com.bej3.seconhand.entities.GambarProduk;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.responses.WebResponse;
import com.bej3.seconhand.services.GambarProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/gambarProduk")
public class GambarProdukController {
    private final GambarProdukService gambarProdukService;

    @Autowired
    public GambarProdukController(GambarProdukService gambarProdukService) {
        this.gambarProdukService = gambarProdukService;
    }


    @RequestMapping(value = "/upload/{idProduk}",method = RequestMethod.POST,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public WebResponse<String> uploadGambarProduk(@RequestParam(value = "upload")MultipartFile file, @PathVariable("idProduk")  int idProduk ) throws IOException{
        gambarProdukService.uploadGambarProduk(file, idProduk);
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "Berhasil Upload Gambar Produk",
                file.getOriginalFilename()
        );
    }

    @GetMapping("{idGambarProduk}")
    public ResponseEntity<byte[]> getGambarProduk(@PathVariable int idGambarProduk) throws NotFoundException {
        GambarProduk gambarProduk = gambarProdukService.getGambarProduk(idGambarProduk);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(gambarProduk.getType()))
                .body(gambarProduk.getGambarProduk());
    }
}
