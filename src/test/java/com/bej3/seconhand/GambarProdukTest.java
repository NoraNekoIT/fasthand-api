package com.bej3.seconhand;


import com.bej3.seconhand.entities.GambarProduk;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.repositories.GambarProdukRepository;
import com.bej3.seconhand.repositories.ProdukRepository;
import com.bej3.seconhand.services.impls.GambarProdukServiceImpl;
import com.bej3.seconhand.utils.HerokuUrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@Slf4j
class GambarProdukTest {

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
    @Order(2)
    void testGetGambarProduk() throws NotFoundException {
        GambarProduk isiGambarProdukmock = new GambarProduk(10,"gambar Product","Gambar Produk 1",null,null);
        Mockito.lenient().when(gambarProdukRepository.findById(10)).thenReturn(Optional.of(isiGambarProdukmock));
        log.info(gambarProdukRepository.findById(10).get().getIdGambarProduk().toString());
        gambarProdukService.getGambarProduk(10).getIdGambarProduk();
        Assertions.assertEquals(gambarProdukRepository.findById(10).get().getIdGambarProduk(),gambarProdukService.getGambarProduk(10).getIdGambarProduk());
    }
}
