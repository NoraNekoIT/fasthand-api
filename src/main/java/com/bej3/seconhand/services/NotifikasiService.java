package com.bej3.seconhand.services;

import com.bej3.seconhand.errors.NotFoundException;
import org.springframework.http.ResponseEntity;

public interface NotifikasiService {
    ResponseEntity<?> getNotifikasiByIdUser(Integer idUser) throws NotFoundException;

}
