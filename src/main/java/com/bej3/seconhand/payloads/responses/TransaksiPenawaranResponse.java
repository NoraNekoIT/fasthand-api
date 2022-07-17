package com.bej3.seconhand.payloads.responses;

import com.bej3.seconhand.entities.Produk;
import com.bej3.seconhand.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransaksiPenawaranResponse {
    private Integer idPenawaran;
    private double hargaTawaran;
    private boolean statusTawaran;
    private boolean statusTransaksi;
    private Integer idPembeli;
    private Integer idProduk;
}
