package com.bej3.seconhand.repositories;

import com.bej3.seconhand.entities.Kategori;
import com.bej3.seconhand.entities.Produk;
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

    //wishlist produk join transaksi dimana id produk = id produk, id penjual apa
    @Query(value = "SELECT DISTINCT p from produk p, transaksi t WHERE p.idProduk = t.produk.idProduk " +
            "AND p.user=:penjual")
    List<Produk> findAllProdukByWishlist(@Param("penjual") Users penjual);

    //wishlist produk join transaksi dimana id produk = id produk, id pembeli nya apa
    @Query(value = "SELECT DISTINCT p from produk p, transaksi t WHERE p.idProduk = t.produk.idProduk " +
            "AND t.user=:pembeli")
    List<Produk> findAllProdukByWishlistPembeli(@Param("pembeli") Users pembeli);

    //transaksi produk join transaksi dimana id produk = id produk dengan status penjualan = true
    @Query(value = "SELECT DISTINCT p from produk p, transaksi t WHERE p.idProduk = t.produk.idProduk " +
            "AND p.user=:penjual " +
            "AND t.statusTransaksi=true")
    List<Produk> findAllProdukByTransaksi(@Param("penjual") Users penjual);
}