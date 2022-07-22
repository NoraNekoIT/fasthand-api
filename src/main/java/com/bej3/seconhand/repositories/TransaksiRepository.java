package com.bej3.seconhand.repositories;

import com.bej3.seconhand.entities.Transaksi;
import com.bej3.seconhand.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Integer> {
//    get list penawaran mau true atau false
//    get list transaksi where status penawaran = true

    // get tawaran
    List<Transaksi> findAllByStatusTransaksiFalseAndProduk_User(Users produk_user);

    // get transaksi
    List<Transaksi> findAllByProduk_User(Users produk_user);

    //    get list wishlist dengan status transaksi = false dan distinct(id_user_pembeli dan id_produk)
//    get list produk dimana transaksi id produk status penjualan true
}