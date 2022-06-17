package com.bej3.seconhand.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "gambar_produk")
public class GambarProduk {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_gambar_produk")
    private int idGambarProduk;

    @Column(name = "gambar_produk")
    private byte[] gambarProduk;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_produk")
    private Produk produk;
}
