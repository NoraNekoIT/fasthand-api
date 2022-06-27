package com.bej3.seconhand.services.impls;

import com.bej3.seconhand.entities.Kategori;
import com.bej3.seconhand.payloads.responses.WebResponse;
import com.bej3.seconhand.repositories.KategoriRepository;
import com.bej3.seconhand.services.KategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KategoriServiceImpl implements KategoriService {

    private final KategoriRepository kategoriRepository;

    @Autowired
    public KategoriServiceImpl(KategoriRepository kategoriRepository) {
        this.kategoriRepository = kategoriRepository;
    }

    @Override
    public WebResponse<String,?> getListKategori() {
        List<Kategori> kategori = kategoriRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("totalItems", kategori.size());
        response.put("kategori",kategori);
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "OK",
                "Berhasil mendapatkan nama-nama Kategori",
                response
        );
    }
}