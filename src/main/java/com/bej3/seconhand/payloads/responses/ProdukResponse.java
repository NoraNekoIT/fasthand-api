package com.bej3.seconhand.payloads.responses;

import com.bej3.seconhand.entities.GambarProduk;
import com.bej3.seconhand.entities.Kategori;
import com.bej3.seconhand.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ProdukResponse {
    private Integer idProduk;
    private boolean statusTerhapus;
    private String namaProduk;
    private boolean statusTerjual;
    private String deskripsiProduk;
    private double hargaProduk;
    private double potonganDiskon;
    private Kategori kategori;
    private Integer idPenjual;
    private Set<String> gambarProdukList;
}