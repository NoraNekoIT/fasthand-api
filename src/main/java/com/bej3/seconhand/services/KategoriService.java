package com.bej3.seconhand.services;

import com.bej3.seconhand.entities.Kategori;
import com.bej3.seconhand.payloads.responses.WebResponse;

import java.util.List;

public interface KategoriService {
    WebResponse<String,?> getListKategori();
}
