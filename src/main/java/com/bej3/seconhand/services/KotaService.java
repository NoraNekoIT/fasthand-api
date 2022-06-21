package com.bej3.seconhand.services;

import com.bej3.seconhand.entities.Kota;
import com.bej3.seconhand.payloads.requests.KotaRequest;

import java.util.List;

public interface KotaService {
    Kota insertKota(KotaRequest kotaRequest);
    List<Kota> getListKota();
}
