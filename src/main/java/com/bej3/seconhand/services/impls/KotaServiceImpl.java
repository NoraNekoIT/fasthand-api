package com.bej3.seconhand.services.impls;

import com.bej3.seconhand.entities.Kota;
import com.bej3.seconhand.payloads.responses.WebResponse;
import com.bej3.seconhand.repositories.KotaRepository;
import com.bej3.seconhand.services.KotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KotaServiceImpl implements KotaService {
    private final KotaRepository kotaRepository;

    @Autowired
    public KotaServiceImpl(KotaRepository kotaRepository) {
        this.kotaRepository = kotaRepository;
    }

    @Override
    public WebResponse<String, ?> getListKota(String namaKota,
                                              Integer pageNo,
                                              Integer pageSize,
                                              String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Kota> pageResult;
        if (namaKota == null)
            pageResult = kotaRepository.findAll(paging);
        else
            pageResult = kotaRepository.findByNamaKotaContaining(namaKota, paging);

        Map<String, Object> response = new HashMap<>();
        response.put("kota", pageResult.getContent());
        response.put("currentPage", pageResult.getNumber());
        response.put("totalItems", pageResult.getTotalElements());
        response.put("totalPages", pageResult.getTotalPages());
        if (pageResult.hasContent()) {
            return new WebResponse<>(
                    HttpStatus.OK.value(),
                    "OK",
                    "Berhasil mendapatkan nama semua kota",
                    response
            );
        } else {
            return new WebResponse<>(
                    HttpStatus.OK.value(),
                    "OK",
                    "Data nama kota tidak ada ",
                    ""
            );
        }

    }
}
