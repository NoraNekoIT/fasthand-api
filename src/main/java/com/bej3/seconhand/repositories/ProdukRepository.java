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
//    @Query("select new com.bej3.seconhand.entities.ProdukTransaksiJoin(p.idProduk,p.statusTerhapus,p.namaProduk,p.statusTerjual,p.deskripsiProduk," +
//            "p.hargaProduk,p.potonganDiskon,p.kategori, t.idTransaksi,p.user.idUser,t.hargaTawaran,t.statusTawaran, t.statusTransaksi )" +
//            " from produk p, transaksi t where t.produk.idProduk = p.idProduk and p.user.idUser =:penjual")
//    List<ProdukTransaksiJoin> findAllProdukByWishlist(@Param("penjual") Users penjual);

    //transaksi produk join transaksi dimana id produk = id produk dengan status penjualan = true
//    @Query("select new com.bej3.seconhand.entities.ProdukTransaksiJoin(p.idProduk,p.statusTerhapus,p.namaProduk,p.statusTerjual,p.deskripsiProduk," +
//            "p.hargaProduk,p.potonganDiskon,p.kategori, t.idTransaksi,p.user.idUser,t.hargaTawaran,t.statusTawaran, t.statusTransaksi )" +
//            " from produk p, transaksi t where t.produk.idProduk = p.idProduk and p.user.idUser =:penjual and t.statusTransaksi=true ")
//    List<ProdukTransaksiJoin> findAllProdukByTransaksi(@Param("penjual") Users penjual);
}