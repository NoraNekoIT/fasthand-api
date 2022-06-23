package com.bej3.seconhand.repositories;

import com.bej3.seconhand.entities.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriRepository extends JpaRepository<Kategori, Integer> {
}
