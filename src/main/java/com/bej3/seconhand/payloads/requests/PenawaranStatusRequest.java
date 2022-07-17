package com.bej3.seconhand.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
public class PenawaranStatusRequest {
    @NotNull(message = "masukkan id penawaran tidak boleh null")
    private Integer idPenawaran;
    @NotNull(message = "masukkan id penjual ")
    private Integer idPenjual;
    private boolean statusTawaran;
}