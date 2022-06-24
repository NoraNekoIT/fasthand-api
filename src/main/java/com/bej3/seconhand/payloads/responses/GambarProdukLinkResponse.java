package com.bej3.seconhand.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class GambarProdukLinkResponse {
    private String linkGambar;

    public String getLinkGambar() {
        return linkGambar;
    }

    @Override
    public String toString() {
        return linkGambar ;
    }
}