package com.bej3.seconhand.entities;

import javax.persistence.*;

@Entity(name = "Produk")
public class Produk {
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    @Column(name = "id_produk")
    private int idProduk;

    @Column(name = "Status Terjual")
    private boolean statusTerjual;

    @Column(name = "Status Delete")
    private boolean statusDelete;

}
