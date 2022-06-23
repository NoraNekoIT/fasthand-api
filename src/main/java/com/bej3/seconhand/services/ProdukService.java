package com.bej3.seconhand.services;
import com.bej3.seconhand.entities.Produk;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.ProdukAddRequest;
import com.bej3.seconhand.payloads.responses.ProdukResponse;

import java.util.stream.Stream;

public interface ProdukService {
    Produk addProduk(ProdukAddRequest produkAddRequest) throws NotFoundException;
    Stream<ProdukResponse> getListProduk();
    Stream<ProdukResponse> getListProdukByPenjual(int idPenjual) throws NotFoundException;
    Stream<ProdukResponse> getListProdukByKategori(int kategori) throws NotFoundException;

}