package com.bej3.seconhand.services.impls;

import com.bej3.seconhand.entities.Users;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.responses.WebResponse;
import com.bej3.seconhand.repositories.NotifikasiRepository;
import com.bej3.seconhand.repositories.UserRepository;
import com.bej3.seconhand.services.NotifikasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotifikasiServiceImpl implements NotifikasiService {
    private final NotifikasiRepository notifikasiRepository;
    private final UserRepository userRepository;
    @Autowired
    public NotifikasiServiceImpl(NotifikasiRepository notifikasiRepository,
                                 UserRepository userRepository
                                 ) {
        this.notifikasiRepository = notifikasiRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> getNotifikasiByIdUser(Integer idUser) throws NotFoundException {
        Users user= userRepository.findById(idUser).orElseThrow(
                ()->new NotFoundException("belum ada notifikasi")
        );
        return ResponseEntity.ok().body(
                new WebResponse<>(
                        HttpStatus.OK.value(),
                        "OK",
                        "Berhasil Mendapatkan Notifikasi",
                        notifikasiRepository.findByUser(user)
                )
        );
    }
}
