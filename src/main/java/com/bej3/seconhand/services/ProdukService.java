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

    ResponseEntity<?> getListProdukWithoutPagination();
    WebResponse<String,?> getListProdukByPenjualWithoutPagination(int idPenjual) throws NotFoundException;
    ResponseEntity<?> getProdukByPenjualWishlistWithoutPagination(Integer idPenjual) throws NotFoundException;
    ResponseEntity<?> getProdukByPenjualTransaksiWithoutPagination(Integer idPenjual) throws NotFoundException;
    ResponseEntity<?> searchProdukByNameWithoutPagination(String nameProduk) throws NotFoundException;
    ResponseEntity<?> sortProdukByKategoriWithoutPagination(Integer kategori) throws NotFoundException;

    ResponseEntity<?> getProdukDetailById(Integer idProduk) throws NotFoundException;

    WebResponse<String,?> deleteProduk(Integer idProduk,
                                       Integer idPenjual) throws NotFoundException;
    ResponseEntity<?> updateProduk(ProdukUpdateRequest produkUpdateRequest) throws NotFoundException, IOException;
}