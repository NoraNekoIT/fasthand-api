package com.bej3.seconhand.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Entity(name = "transaksi")
public class Transaksi {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaksi")
    private Integer idTransaksi;

    @Column(name = "harga_tawaran")
    private double hargaTawaran;

    @Column(name = "status_tawaran")
    private boolean statusTawaran;

    @Column(name = "status_transaksi")
    private boolean statusTransaksi;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_user_pembeli")
    private Users user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_produk")
    private Produk produk;

    public Transaksi(double hargaTawaran,
                     boolean statusTawaran,
                     boolean statusTransaksi,
                     Users user,
                     Produk produk) {
        this.hargaTawaran = hargaTawaran;
        this.statusTawaran = statusTawaran;
        this.statusTransaksi = statusTransaksi;
        this.user = user;
        this.produk = produk;
    }

    public Transaksi() {

    }
}