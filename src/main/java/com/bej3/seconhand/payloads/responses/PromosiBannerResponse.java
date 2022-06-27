package com.bej3.seconhand.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PromosiBannerResponse {
    private Integer idPromosi;
    private String labelPromosi;
    private String linkGambar;
}
