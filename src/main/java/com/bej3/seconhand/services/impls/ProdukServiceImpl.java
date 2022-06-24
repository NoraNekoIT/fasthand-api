package com.bej3.seconhand.services.impls;

import com.bej3.seconhand.entities.Kategori;
import com.bej3.seconhand.entities.Produk;
import com.bej3.seconhand.entities.Users;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.ProdukAddRequest;
import com.bej3.seconhand.payloads.responses.ProdukResponse;
import com.bej3.seconhand.repositories.KategoriRepository;
import com.bej3.seconhand.repositories.ProdukRepository;
import com.bej3.seconhand.repositories.UserRepository;
import com.bej3.seconhand.services.GambarProdukService;
import com.bej3.seconhand.services.ProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProdukServiceImpl implements ProdukService {

    private final ProdukRepository produkRepository;
    private final UserRepository userRepository;
    private final KategoriRepository kategoriRepository;

    private final GambarProdukService gambarProdukService;

    @Autowired
    public ProdukServiceImpl(ProdukRepository produkRepository,
                             UserRepository userRepository,
                             KategoriRepository kategoriRepository,
                             GambarProdukService gambarProdukService
    ) {
        this.produkRepository = produkRepository;
        this.userRepository = userRepository;
        this.kategoriRepository = kategoriRepository;
        this.gambarProdukService = gambarProdukService;
    }

    @Override
    public Produk addProduk(ProdukAddRequest produkAddRequest) throws NotFoundException {
        System.out.println(produkAddRequest.getIdPenjual());
        Users userPenjual = userRepository.
                findById(produkAddRequest.getIdPenjual()).
                orElseThrow(NotFoundException::new);
        Kategori kategoriProduk = kategoriRepository.
                findById(produkAddRequest.getIdKategori()).orElseThrow(NotFoundException::new);
        Produk produk = new Produk(
                produkAddRequest.isStatusTerhapus(),
                produkAddRequest.getNamaProduk(),
                produkAddRequest.isStatusTerjual(),
                produkAddRequest.getDeskripsiProduk(),
                produkAddRequest.getHargaProduk(),
                0
        );
        produk.setUser(userPenjual);
        produk.setKategori(kategoriProduk);
        return produkRepository.save(produk);
    }

    @Override
    public Stream<ProdukResponse> getListProduk() {
        return produkRepository.findAll().stream().map(this::convertProdukToProdukResponse);
    }

    @Override
    public Stream<ProdukResponse> getListProdukByPenjual(int idPenjual) throws NotFoundException {
        Users penjual = userRepository.findById(idPenjual).orElseThrow(NotFoundException::new);
        return produkRepository.findAllProdukByPenjual(penjual)
                .stream().map(this::convertProdukToProdukResponse);
    }

    @Override
    public Stream<ProdukResponse> getListProdukByKategori(int idKategori) throws NotFoundException {
        Kategori kategori = kategoriRepository.findById(idKategori).orElseThrow(NotFoundException::new);
        return produkRepository.findAllProdukByKategori(kategori).
                stream().map(this::convertProdukToProdukResponse);
    }

    @Override
    public void deleteProduk(int idProduk) {
        produkRepository.deleteById(idProduk);
    }

    private ProdukResponse convertProdukToProdukResponse(Produk produk) {
        return new ProdukResponse(
                produk.getIdProduk(),
                produk.isStatusTerhapus(),
                produk.getNamaProduk(),
                produk.isStatusTerjual(),
                produk.getDeskripsiProduk(),
                produk.getHargaProduk(),
                produk.getPotonganDiskon(),
                produk.getKategori(),
                gambarProdukService.getGambarProdukByIdProduk(
                        produk.getIdProduk()
                ).map(String::valueOf).collect(Collectors.toSet())
        );
    }

}