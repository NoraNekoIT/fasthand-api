package com.bej3.seconhand.services;

import com.bej3.seconhand.entities.GambarProduk;
import com.bej3.seconhand.errors.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface GambarProdukService {
    String uploadGambarProduk(MultipartFile file, int idProduk) throws IOException;
//    GambarProduk getInfoImageByName(String namaGambar);
    GambarProduk getGambarProduk(int id) throws NotFoundException;
}