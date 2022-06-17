package com.bej3.seconhand.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "transaksi")
public class Transaksi {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "id_transaksi")
    private int idTransaksi;

    @Column(name = "harga_tawaran")
    private int harga_tawaran;

    @Column(name = "status_tawaran")
    private boolean status_tawaran;

    @Column(name = "status_transaksi")
    private boolean status_transaksi;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_user_pembeli")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_produk")
    private Produk produk;
}
