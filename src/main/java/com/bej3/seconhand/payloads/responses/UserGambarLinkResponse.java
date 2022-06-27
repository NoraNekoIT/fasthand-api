package com.bej3.seconhand.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserGambarLinkResponse {
    private String linkGambar;

    @Override
    public String toString() {
        return linkGambar ;
    }
}
