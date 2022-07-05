package com.bej3.seconhand.services;

import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.PenawaranCreateRequest;
import com.bej3.seconhand.payloads.requests.PenawaranStatusRequest;
import com.bej3.seconhand.payloads.requests.TransaksiStatusRequest;
import org.springframework.http.ResponseEntity;

public interface TransaksiService {
    ResponseEntity<?> addPenawaran(PenawaranCreateRequest penawaranCreateRequest)
            throws NotFoundException;
    ResponseEntity<?> changeStatePenawaran(PenawaranStatusRequest penawaranStatusRequest) throws NotFoundException;
    ResponseEntity<?> changeStateTransaksi(TransaksiStatusRequest transaksiStatusRequest) throws NotFoundException;

//    //get penawaran by penjual
//    ResponseEntity<?> getPenawaranByPenjual(Integer idPenjual );
//    //get transaksi by penjual
//    ResponseEntity<?> getTransaksiByPenjual(Integer idPenjual);

}