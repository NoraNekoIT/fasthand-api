package com.bej3.seconhand.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "gambar_produk")
public class GambarProduk {

    @Id
    @Column(name = "id_gambar_produk")
    private int idGambarProduk;
}
