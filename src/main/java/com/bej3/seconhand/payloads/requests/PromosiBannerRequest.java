package com.bej3.seconhand.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PromosiBannerRequest {
    private int idPromosi;

    private byte[] gambarBanner;

    private String labelPromosi;
}
