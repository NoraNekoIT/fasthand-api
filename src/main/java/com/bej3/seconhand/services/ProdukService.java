package com.bej3.seconhand.services;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.ProdukAddRequest;
import com.bej3.seconhand.payloads.requests.ProdukUpdateRequest;
import com.bej3.seconhand.payloads.responses.WebResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ProdukService {
    ResponseEntity<?> addProduk(ProdukAddRequest produkAddRequest) throws NotFoundException, IOException;
    WebResponse<String,?> getListProduk(String namaProduk,
                                        Integer pageNo,
                                        Integer pageSize,
                                        Integer idKategori
                                        ) throws NotFoundException;
    WebResponse<String,?> getListProdukByPenjual(int idPenjual) throws NotFoundException;
//    WebResponse<String,?> getListProdukByKategori(int kategori) throws NotFoundException;
    WebResponse<String,?> deleteProduk(Integer idProduk,
                                       Integer idPenjual) throws NotFoundException;
    WebResponse<String,?> updateProduk(ProdukUpdateRequest produkUpdateRequest) throws NotFoundException;
}