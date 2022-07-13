package com.bej3.seconhand;

import com.bej3.seconhand.entities.Kota;
import com.bej3.seconhand.repositories.KotaRepository;
import com.bej3.seconhand.services.impls.KotaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@ExtendWith(MockitoExtension.class)
public class KotaTest {
    @Mock KotaRepository kotaRepository;

    private KotaServiceImpl kotaService;

    @BeforeEach void setUp() {
        this.kotaService = new KotaServiceImpl(this.kotaRepository);
    }
    @Test
    @DisplayName("Test Get all kategori")
    void testGetAllKota() {

        System.out.println(kotaService.getListKota("K", 1, 10, "idKota"));
        Pageable paging = PageRequest.of(1, 10, Sort.by("idKota"));
        Mockito.verify(kotaRepository).findByNamaKotaContaining("K",paging);
    }
}
