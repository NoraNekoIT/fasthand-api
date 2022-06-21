package com.bej3.seconhand.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "produk")
public class Produk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produk")
    private int idProduk;

    @Column(name = "status_terhapus")
    private boolean statusTerhapus;

    @Column(name = "nama_produk")
    private String namaProduk;

    @Column(name = "status_terjual")
    private boolean statusTerjual;

    @Column(name = "deskripsi_produk")
    private String deskripsiProduk;

    @Column(name = "harga_produk")
    private double hargaProduk;

    @Column(name = "potongan_diskon")
    private double potonganDiskon;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_kategori")
    private Kategori kategori;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_user_penjual")
    private Users user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_promosi", nullable = true)
    private PromosiBanner promosiBanner;

//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<GambarProduk> gambarProdukList;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<Wishlist> wishlists ;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<Transaksi> transaksiList ;
}