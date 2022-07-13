package com.bej3.seconhand;


import com.bej3.seconhand.repositories.GambarProdukRepository;
import com.bej3.seconhand.services.impls.GambarProdukServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GambarProdukTest {

    @Mock GambarProdukRepository gambarProdukRepository;

    private GambarProdukServiceImpl gambarProdukService;

    @BeforeEach void setUp() { this.gambarProdukService = new GambarProdukServiceImpl(this.gambarProdukRepository); }

    @Test
    @DisplayName("Test Upload Gambar Produk")
    void testUploadGambarProduk() {
        gambarProdukService.uploadGambarProduk("upload",1);
    }

}
