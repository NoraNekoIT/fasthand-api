package com.bej3.seconhand;


import com.bej3.seconhand.repositories.KotaRepository;
import com.bej3.seconhand.services.impls.KotaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;



@ExtendWith(MockitoExtension.class)
@Slf4j
class KotaTest {
    @Mock KotaRepository kotaRepository;

    @Autowired
    private KotaServiceImpl kotaService;

    @BeforeEach void setUp() {
        this.kotaService = new KotaServiceImpl(this.kotaRepository);
    }
    @Test
    @DisplayName("Test Get all kota Without Pagination")
    void testGetAllKotawithoutpagination() {
        kotaService.getListKotaWithoutPagination();
        Mockito.verify(kotaRepository).findAll();
    }
}
