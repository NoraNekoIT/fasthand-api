package com.bej3.seconhand.services.impls;

import com.bej3.seconhand.entities.Kota;
import com.bej3.seconhand.payloads.requests.KotaRequest;
import com.bej3.seconhand.repositories.KotaRepository;
import com.bej3.seconhand.services.KotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KotaServiceImpl implements KotaService {
    private final KotaRepository kotaRepository;

    @Autowired
    public KotaServiceImpl(KotaRepository kotaRepository) {
        this.kotaRepository = kotaRepository;
    }

    @Override
    public Kota insertKota(KotaRequest kotaRequest) {
        Kota kota = new Kota(
                kotaRequest.getNamaKota()
        );
        return kotaRepository.save(kota);
    }

    @Override
    public List<Kota> getListKota() {
        return kotaRepository.findAll();
    }
}
