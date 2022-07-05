package com.bej3.seconhand.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ProdukTransaksiJoin implements Serializable {
    private Integer idProduk;
    private boolean statusTerhapus;
    private String namaProduk;
    private boolean statusTerjual;
    private String deskripsiProduk;
    private double hargaProduk;
    private double potonganDiskon;
    private Kategori kategori;
    private Integer idTransaksi;
    private Integer idPenjual;
    private Integer idPromosi;
    private Integer idPembeli;
    private double hargaTawaran;
    private boolean statusTawaran;
    private boolean statusTransaksi;
}