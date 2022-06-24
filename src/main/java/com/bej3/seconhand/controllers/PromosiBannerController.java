package com.bej3.seconhand.controllers;

import com.bej3.seconhand.entities.PromosiBanner;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.responses.WebResponse;
import com.bej3.seconhand.services.PromosiBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/promosiBanner")
public class PromosiBannerController {

    private final PromosiBannerService promosiBannerService;

    @Autowired
    public PromosiBannerController(PromosiBannerService promosiBannerService) {
        this.promosiBannerService = promosiBannerService;
    }

    @RequestMapping(value = "/upload/{idPromosi}", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public WebResponse<String> uploadPromosiBanner(@RequestParam(value = "upload") MultipartFile file, @PathVariable("idPromosi") int idPromosi) throws IOException {
        promosiBannerService.uploadPromosiBanner(file, idPromosi);
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "Berhasil Upload Promosi Banner",
                file.getOriginalFilename()
        );
    }

    @GetMapping("{idPromosiBanner}")
    public ResponseEntity<byte[]> getPromosiBanner(@PathVariable("idPromosiBanner")
                                            int idPromosiBanner) throws NotFoundException {
        PromosiBanner promosiBanner = promosiBannerService.getPromosiBanner(idPromosiBanner);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(promosiBanner.getTypeGambarBanner()))
                .body(promosiBanner.getGambarBanner());
    }
}