package com.bej3.seconhand.services.impls;

import com.bej3.seconhand.entities.GambarProduk;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.repositories.GambarProdukRepository;
import com.bej3.seconhand.services.GambarProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class GambarProdukServiceImpl implements GambarProdukService {
    private final GambarProdukRepository gambarProdukRepository;

    @Autowired
    public GambarProdukServiceImpl(GambarProdukRepository gambarProdukRepository) {
        this.gambarProdukRepository = gambarProdukRepository;
    }

    @Override
    public String uploadGambarProduk(MultipartFile file, int idProduk) throws IOException {
        GambarProduk gambarProduk = new GambarProduk(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes()
        );
        gambarProdukRepository.save(gambarProduk);
        return "sukses upload gambar " + file.getOriginalFilename();
    }

    @Override
    public GambarProduk getGambarProduk(int id) throws NotFoundException {
        GambarProduk gambarProduk = gambarProdukRepository.findById(id).orElseThrow(NotFoundException::new);
        return gambarProduk;
    }

}