package com.bej3.seconhand.services;

import com.bej3.seconhand.entities.Kota;
import com.bej3.seconhand.payloads.requests.KotaRequest;
import com.bej3.seconhand.payloads.responses.WebResponse;
import org.springframework.http.ResponseEntity;


public interface KotaService {
    //    Kota insertKota(KotaRequest kotaRequest);
    WebResponse<String, ?> getListKota(String namaKota,
                                                Integer pageNo,
                                                Integer pageSize,
                                                String sortBy);

    ResponseEntity<?> getListKotaWithoutPagination();
}
