package com.bej3.seconhand.entities;

import javax.persistence.*;

@Entity(name = "transaksi")
public class Transaksi {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "id_transaksi")
    private int idTransaksi;

    @Column(name = "status_transaksi")
    private int statusTransaksi;
}
