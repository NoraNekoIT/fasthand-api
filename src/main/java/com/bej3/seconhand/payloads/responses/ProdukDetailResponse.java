package com.bej3.seconhand.payloads.responses;

import com.bej3.seconhand.entities.Kategori;
import com.bej3.seconhand.entities.Kota;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@AllArgsConstructor
@Setter
public class ProdukDetailResponse {
    private Integer idProduk;
    private boolean statusTerhapus;
    private String namaProduk;
    private boolean statusTerjual;
    private String deskripsiProduk;
    private double hargaProduk;
    private double potonganDiskon;
    private Kategori kategori;
    private Integer idPenjual;
    private String namaPenjual;
    private UserGambarLinkResponse gambarPenjual;
    private Kota kota;
    private Set<String> gambarProdukList;

}
