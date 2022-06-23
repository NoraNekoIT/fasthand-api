package com.bej3.seconhand.payloads.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdukAddRequest {
    private boolean statusTerhapus;
    private String namaProduk;
    private boolean statusTerjual;
    private String deskripsiProduk;
    private double hargaProduk;

    private int idKategori;
    private int idPenjual;
//    private double potonganDiskon;
}
