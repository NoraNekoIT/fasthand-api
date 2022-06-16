package com.bej3.seconhand.entities;

import javax.persistence.*;

@Entity(name = "produk_detail")
public class ProdukDetails {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "nama_produk")
    private int namaProduk;

    @Column(name = "deskripsi_produk")
    private int deskripsiProduk;

    @Column(name = "harga_produk")
    private int hargaProduk;

    @Column(name = "potongan_diskon")
    private int potonganDiskon;
}
