package com.bej3.seconhand.repositories;

import com.bej3.seconhand.entities.Kota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KotaRepository extends JpaRepository<Kota,Integer> {
}