package com.bej3.seconhand.services.impls;

import com.bej3.seconhand.entities.Kategori;
import com.bej3.seconhand.repositories.KategoriRepository;
import com.bej3.seconhand.services.KategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KategoriServiceImpl implements KategoriService {

    private final KategoriRepository kategoriRepository;

    @Autowired
    public KategoriServiceImpl(KategoriRepository kategoriRepository) {
        this.kategoriRepository = kategoriRepository;
    }

    @Override
    public List<Kategori> getListKategori() {
        return kategoriRepository.findAll();
    }
}
