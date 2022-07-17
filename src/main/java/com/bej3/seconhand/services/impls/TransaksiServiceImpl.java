package com.bej3.seconhand.services.impls;

import com.bej3.seconhand.entities.Notifikasi;
import com.bej3.seconhand.entities.Produk;
import com.bej3.seconhand.entities.Transaksi;
import com.bej3.seconhand.entities.Users;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.PenawaranCreateRequest;
import com.bej3.seconhand.payloads.requests.PenawaranStatusRequest;
import com.bej3.seconhand.payloads.requests.TransaksiStatusRequest;
import com.bej3.seconhand.payloads.responses.WebResponse;
import com.bej3.seconhand.repositories.NotifikasiRepository;
import com.bej3.seconhand.repositories.ProdukRepository;
import com.bej3.seconhand.repositories.TransaksiRepository;
import com.bej3.seconhand.repositories.UserRepository;
import com.bej3.seconhand.services.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransaksiServiceImpl implements TransaksiService {

    private final TransaksiRepository transaksiRepository;
    private final ProdukRepository produkRepository;
    private final UserRepository userRepository;
    private final NotifikasiRepository notifikasiRepository;

    @Autowired
    public TransaksiServiceImpl(TransaksiRepository transaksiRepository,
                                ProdukRepository produkRepository,
                                UserRepository userRepository,
                                NotifikasiRepository notifikasiRepository) {
        this.transaksiRepository = transaksiRepository;
        this.produkRepository = produkRepository;
        this.userRepository = userRepository;
        this.notifikasiRepository = notifikasiRepository;
    }

    @Override
    public ResponseEntity<?> addPenawaran(PenawaranCreateRequest penawaranCreateRequest) throws NotFoundException {
        Produk checkProduk = produkRepository.findById(penawaranCreateRequest.getIdProduk()).orElseThrow(
                () -> new NotFoundException("id produk tidak ditemukan")
        );
        Users checkUserPembeli = userRepository.findById(penawaranCreateRequest.getIdUserPembeli()).orElseThrow(
                () -> new NotFoundException("id user tidak ada")
        );

        if (checkProduk.getUser().getIdUser() == checkUserPembeli.getIdUser()) {
            return ResponseEntity.badRequest().body(
                    new WebResponse<>(
                            HttpStatus.BAD_REQUEST.value(),
                            "BAD REQUEST",
                            "pembeli tidak boleh membeli barang sendiri",
                            ""
                    )
            );
        } else {
            Transaksi addPenawarn = new Transaksi(
                    penawaranCreateRequest.getHargaTawaran(),
                    false,
                    false,
                    checkUserPembeli,
                    checkProduk
            );
            //pembeli
            Notifikasi addNotifikasiPembeli = new Notifikasi(
                    "Kamu melakukan penawaran produk "+checkProduk.getNamaProduk() +"silahkan tunggu response penjual",
                    checkUserPembeli,
                    addPenawarn
            );
            //penjual
            Notifikasi addNotifikasiPenjual = new Notifikasi(
                    "ada yang melakukan penawaran produk "+checkProduk.getNamaProduk() +"kamu. Silahkan konfirmasi",
                    checkProduk.getUser(),
                    addPenawarn
            );

            transaksiRepository.save(addPenawarn);
            notifikasiRepository.save(addNotifikasiPembeli);
            notifikasiRepository.save(addNotifikasiPenjual);
            return ResponseEntity.ok().body(
                    new WebResponse<>(
                            HttpStatus.OK.value(),
                            "OK",
                            "berhasil membuat penawaran silahkan tunggu persetujuan dari penjual",
                            ""
                    )
            );
        }


    }

    @Override
    public ResponseEntity<?> changeStatePenawaran(PenawaranStatusRequest penawaranStatusRequest) throws NotFoundException {

        Transaksi checkPenawaran = transaksiRepository.findById(penawaranStatusRequest.getIdPenawaran()).orElseThrow(
                () -> new NotFoundException("id penawaran tidak ditemukan")
        );

        Users checkPenjual = userRepository.findById(penawaranStatusRequest.getIdPenjual()).orElseThrow(
                () -> new NotFoundException("id penjual tidak ada")
        );

        if (checkPenawaran.getProduk().getUser().getIdUser() != checkPenjual.getIdUser()) {
            return ResponseEntity.badRequest().body(
                    new WebResponse<>(
                            HttpStatus.BAD_REQUEST.value(),
                            "BAD REQUEST",
                            "anda tidak memiliki akses ubah state penawaran",
                            ""
                    )
            );
        }
        checkPenawaran.setStatusTawaran(penawaranStatusRequest.isStatusTawaran());
        transaksiRepository.save(checkPenawaran);
        Notifikasi addNotifikasiPembeli;
        if (checkPenawaran.isStatusTawaran()==true){
            //pembeli
            addNotifikasiPembeli = new Notifikasi(
                    "Penjual menyetujui penawaran produk"+checkPenawaran.getProduk().getNamaProduk() ,
                    checkPenawaran.getUser(),
                    checkPenawaran
            );
        }else {
            //pembeli
            addNotifikasiPembeli = new Notifikasi(
                    "Penjual tidak menyetujui penawaran produk"+checkPenawaran.getProduk().getNamaProduk() ,
                    checkPenawaran.getUser(),
                    checkPenawaran
            );
        }
        notifikasiRepository.save(addNotifikasiPembeli);
        return ResponseEntity.ok().body(
                new WebResponse<>(
                        HttpStatus.OK.value(),
                        "OK",
                        "berhasil mengubah state penawaran",
                        ""
                )
        );
    }

    @Override
    public ResponseEntity<?> changeStateTransaksi(TransaksiStatusRequest transaksiStatusRequest) throws NotFoundException {
        Transaksi checkTransaksi = transaksiRepository.findById(transaksiStatusRequest.getIdTransaksi()).orElseThrow(
                () -> new NotFoundException("id transaksi tidak ditemukan")
        );

        Users checkPenjual = userRepository.findById(transaksiStatusRequest.getIdPenjual()).orElseThrow(
                () -> new NotFoundException("id penjual tidak ada")
        );
        if (checkTransaksi.getProduk().getUser().getIdUser() != checkPenjual.getIdUser()) {
            return ResponseEntity.badRequest().body(
                    new WebResponse<>(
                            HttpStatus.BAD_REQUEST.value(),
                            "BAD REQUEST",
                            "anda tidak memiliki akses ubah state penawaran",
                            ""
                    )
            );
        }
        checkTransaksi.getProduk().setStatusTerjual(true);
        checkTransaksi.setStatusTransaksi(transaksiStatusRequest.isStatusTransaksi());
        transaksiRepository.save(checkTransaksi);
        Notifikasi addNotifikasiPembeli;
        if (checkTransaksi.isStatusTawaran()==true){
            //pembeli
            addNotifikasiPembeli = new Notifikasi(
                    "Penjual sudah menjual produk "+checkTransaksi.getProduk().getNamaProduk()+" ke kamu" ,
                    checkTransaksi.getUser(),
                    checkTransaksi
            );
        }else {
            //pembeli
            addNotifikasiPembeli = new Notifikasi(
                    "Penjual membatalkan menjual produk"+checkTransaksi.getProduk().getNamaProduk() ,
                    checkTransaksi.getUser(),
                    checkTransaksi
            );
        }
        notifikasiRepository.save(addNotifikasiPembeli);
        return ResponseEntity.ok().body(
                new WebResponse<>(
                        HttpStatus.OK.value(),
                        "OK",
                        "berhasil mengubah state penjualan",
                        ""
                )
        );
    }

    @Override
    public ResponseEntity<?> getPenawaranByPenjual(Integer idPenjual) {
        return null;
    }

    @Override
    public ResponseEntity<?> getTransaksiByPenjual(Integer idPenjual) {
        return null;
    }

}