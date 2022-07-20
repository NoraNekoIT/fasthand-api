package com.bej3.seconhand;

import com.bej3.seconhand.entities.*;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.repositories.NotifikasiRepository;
import com.bej3.seconhand.repositories.UserRepository;
import com.bej3.seconhand.services.impls.NotifikasiServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Date;

import java.util.Optional;



@ExtendWith(MockitoExtension.class)
@Slf4j
class NotifikasiTest {

    @Mock NotifikasiRepository notifikasiRepository;

    @Mock UserRepository userRepository;


    @Autowired
    private NotifikasiServiceImpl notifikasiService;

    @BeforeEach void setUp () { this.notifikasiService = new NotifikasiServiceImpl(
            this.notifikasiRepository,
            this.userRepository);
    }

    @Test
    @DisplayName("Test Get Notifikasi by id")
    @Order(2)
    void testNotifikasibyid() throws NotFoundException {
        Users isiUsersMock = new Users(1,"Ari Satria Wiratama","Ari@gmail.com","123123",null,null);
        Mockito.lenient().when(userRepository.findById(1)).thenReturn(Optional.of(isiUsersMock));
        Notifikasi isiNotifikasiMock = new Notifikasi(1,"Pesan notifikasi",new Date(),
                new Users(11,"Ari Satria Wiratama","Ari@gmail.com","123123",null,null), null);
        Mockito.lenient().when(notifikasiRepository.findById(1)).thenReturn(Optional.of(isiNotifikasiMock));
        log.info(notifikasiRepository.findById(1).get().getIdNotifikasi().toString());
        log.info(notifikasiService.getNotifikasiByIdUser(1).getStatusCode().toString());
        Assertions.assertEquals(HttpStatus.OK.value(),notifikasiService.getNotifikasiByIdUser(1).getStatusCodeValue());
    }

}
