package com.bej3.seconhand.repositories;

import com.bej3.seconhand.entities.GambarProduk;
import com.bej3.seconhand.entities.Produk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GambarProdukRepository extends JpaRepository<GambarProduk, Integer> {
    @Query(value = "SELECT g FROM gambar_produk g WHERE g.produk =:produk")
    List<GambarProduk> findAllGambarProdukByProduk(@Param("produk") Optional<Produk> produk);
}