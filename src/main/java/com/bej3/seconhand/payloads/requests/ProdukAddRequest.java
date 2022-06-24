package com.bej3.seconhand.payloads.requests;

import lombok.Setter;


@Setter
public class ProdukAddRequest {
    private boolean statusTerhapus;
    private String namaProduk;
    private boolean statusTerjual;
    private String deskripsiProduk;
    private double hargaProduk;
    private int idKategori;
    private int idPenjual;

    public boolean isStatusTerhapus() {
        return statusTerhapus;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public boolean isStatusTerjual() {
        return statusTerjual;
    }

    public String getDeskripsiProduk() {
        return deskripsiProduk;
    }

    public double getHargaProduk() {
        return hargaProduk;
    }

    public int getIdKategori() {
        return idKategori;
    }

    public int getIdPenjual() {
        return idPenjual;
    }
    //    private double potonganDiskon;
}