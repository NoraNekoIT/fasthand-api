package com.bej3.seconhand.payloads.requests;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class PenawaranCreateRequest {
    private double hargaTawaran;
    @NotNull(message = "masukkan id user pembeli")
    private Integer idUserPembeli;
    @NotNull(message = "masukkan id produk")
    private Integer idProduk;
}