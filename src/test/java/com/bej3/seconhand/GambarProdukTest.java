package com.bej3.seconhand;


import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.repositories.GambarProdukRepository;
import com.bej3.seconhand.repositories.ProdukRepository;
import com.bej3.seconhand.services.impls.GambarProdukServiceImpl;
import com.bej3.seconhand.utils.HerokuUrlUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
public class GambarProdukTest {

    @Mock
    GambarProdukRepository gambarProdukRepository;

    @Mock ProdukRepository produkRepository;

    @Autowired
    private GambarProdukServiceImpl gambarProdukService;

    @BeforeEach void setUp() { this.gambarProdukService= new GambarProdukServiceImpl(
            this.gambarProdukRepository,
            this.produkRepository,
            new HerokuUrlUtil());
    }

    @Test
    @DisplayName("Test get Gambar Product")
    void testUploadGambarProduk() throws NotFoundException {
        System.out.println(gambarProdukService.getGambarProduk(3));
//        Mockito.verify(gambarProdukRepository).findById(1);
    }
}
