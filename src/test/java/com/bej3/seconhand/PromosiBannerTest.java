package com.bej3.seconhand;

import com.bej3.seconhand.entities.PromosiBanner;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.repositories.PromosiBannerRepository;
import com.bej3.seconhand.services.impls.PromosiBannerServiceImpl;
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
class PromosiBannerTest {

    @Mock
    PromosiBannerRepository promosiBannerRepository;

    @Autowired
    private PromosiBannerServiceImpl promosiBannerService;

    @BeforeEach void SetUp() { this.promosiBannerService = new PromosiBannerServiceImpl(
        this.promosiBannerRepository,
        new HerokuUrlUtil());
    }

    @Test
    @DisplayName("Test get Promosi Banner")
    @Order(2)
    void testPromosiBanner() throws NotFoundException {
        PromosiBanner isiPromosiBannerMock = new PromosiBanner(1,null,"nama Gambar banner","diskon 60%");
        Mockito.lenient().when(promosiBannerRepository.findById(1)).thenReturn(Optional.of(isiPromosiBannerMock));
        log.info(promosiBannerRepository.findById(1).get().getIdPromosi().toString());
        promosiBannerService.getPromosiBanner(1).getIdPromosi();
        Assertions.assertEquals(promosiBannerRepository.findById(1).get().getIdPromosi(),promosiBannerService.getPromosiBanner(1).getIdPromosi());
    }

    @Test
    @DisplayName("Test Get all Promosi Banner")
    void testAllPromosiBanner(){
        promosiBannerService.getAllPromosiBanner();
        Mockito.verify(promosiBannerRepository).findAll();
    }
}
