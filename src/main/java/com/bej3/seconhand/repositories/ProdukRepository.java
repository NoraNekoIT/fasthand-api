package com.bej3.seconhand.repositories;

import com.bej3.seconhand.entities.Kategori;
import com.bej3.seconhand.entities.Produk;
import com.bej3.seconhand.entities.ProdukTransaksiJoin;
import com.bej3.seconhand.entities.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdukRepository extends JpaRepository<Produk, Integer> {

    List<Produk> findByNamaProdukContains(String namaProduk);

    List<Produk> findByNamaProdukContainsAndKategori(String namaProduk, Kategori kategori);

    List<Produk> findByUser(Users user);

    @Query(value = "SELECT p FROM produk p WHERE p.kategori =:kategori")
    List<Produk> findAllProdukByKategori(@Param("kategori") Kategori kategori);

    @Query(value = "SELECT p FROM produk p WHERE p.user =:penjual")
    List<Produk> findAllProdukByPenjual(@Param("penjual") Users penjual);

}
