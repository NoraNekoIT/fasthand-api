package com.bej3.seconhand.payloads.requests;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class TransaksiStatusRequest {
    @NotNull(message = "masukkan id transaksi tidak boleh null")
    private Integer idTransaksi;
    @NotNull(message = "masukkan id penjual ")
    private Integer idPenjual;
    private boolean statusTransaksi;
}
