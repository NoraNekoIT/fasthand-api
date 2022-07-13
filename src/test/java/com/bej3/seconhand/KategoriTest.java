package com.bej3.seconhand;

import com.bej3.seconhand.repositories.KategoriRepository;
import com.bej3.seconhand.services.impls.KategoriServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class KategoriTest {
    @Mock KategoriRepository kategoriRepository;

    private KategoriServiceImpl kategoriService;

    @BeforeEach void setUp(){
        this.kategoriService = new KategoriServiceImpl(this.kategoriRepository);
    }
    @Test
    @DisplayName("Test Get all kategori")
    void testGetAllKategori() {
        kategoriService.getListKategori();
        Mockito.verify(kategoriRepository).findAll();
       }


}
