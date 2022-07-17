package com.bej3.seconhand.services;

import com.bej3.seconhand.entities.GambarProduk;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.responses.GambarProdukLinkResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface GambarProdukService {
    void uploadGambarProduk(MultipartFile file, int idProduk) throws IOException, NotFoundException;
    //    GambarProduk getInfoImageByName(String namaGambar);
    GambarProduk getGambarProduk(int id) throws NotFoundException;
    Stream<GambarProdukLinkResponse> getGambarProdukByIdProduk(int idProduk);
}