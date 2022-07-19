package com.bej3.seconhand.services.impls;

import com.bej3.seconhand.entities.*;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.ProdukAddRequest;
import com.bej3.seconhand.payloads.requests.ProdukUpdateRequest;
import com.bej3.seconhand.payloads.responses.ProdukDetailResponse;
import com.bej3.seconhand.payloads.responses.ProdukResponse;
import com.bej3.seconhand.payloads.responses.UserGambarLinkResponse;
import com.bej3.seconhand.payloads.responses.WebResponse;
import com.bej3.seconhand.repositories.*;
import com.bej3.seconhand.services.GambarProdukService;
import com.bej3.seconhand.services.ProdukService;
import com.bej3.seconhand.utils.HerokuUrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProdukServiceImpl implements ProdukService {

    private final ProdukRepository produkRepository;
    private final UserRepository userRepository;
    private final KategoriRepository kategoriRepository;
    private final HerokuUrlUtil herokuUrlUtil;
    private final NotifikasiRepository notifikasiRepository;
    private final GambarProdukService gambarProdukService;

    @Autowired
    public ProdukServiceImpl(ProdukRepository produkRepository,
                             UserRepository userRepository,
                             KategoriRepository kategoriRepository,
                             HerokuUrlUtil herokuUrlUtil,
                             NotifikasiRepository notifikasiRepository,
                             GambarProdukService gambarProdukService
    ) {
        this.produkRepository = produkRepository;
        this.userRepository = userRepository;
        this.kategoriRepository = kategoriRepository;
        this.herokuUrlUtil = herokuUrlUtil;
        this.notifikasiRepository = notifikasiRepository;
        this.gambarProdukService = gambarProdukService;
    }

    @Override
    public ResponseEntity<?> addProduk(ProdukAddRequest produkAddRequest) throws NotFoundException, IOException {

        if (produkAddRequest.getFile().size()>5){
            return ResponseEntity.badRequest().body( new WebResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "BAD REQUEST",
                    "upload gambar produk tidak boleh lebih dari 5",
                    ""
            ));
        }
        List<MultipartFile> files = produkAddRequest.getFile();
        Users userPenjual = userRepository.
                findById(produkAddRequest.getIdPenjual()).orElseThrow(
                        ()->new NotFoundException("id penjual tidak ditemukan"));

        List<Produk> checkProduk = produkRepository.findByUser(userPenjual);
        if (checkProduk.size()>4){
            return ResponseEntity.badRequest().body( new WebResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "BAD REQUEST",
                    "postingan jualan tidak boleh lebih dari 4",
                    ""
            ));
        }

        Kategori kategoriProduk = kategoriRepository.
                findById(produkAddRequest.getIdKategori()).orElseThrow(
                        ()-> new NotFoundException("id kategori tidak ditemukan"));
        Produk produk = new Produk(
                produkAddRequest.getNamaProduk(),
                produkAddRequest.getDeskripsiProduk(),
                produkAddRequest.getHargaProduk(),
                0
        );

        produk.setUser(userPenjual);
        produk.setKategori(kategoriProduk);
        Produk produkAdd = produkRepository.save(produk);
        for (int file=0; file<files.size(); file++) {
            gambarProdukService.uploadGambarProduk(files.get(file),
                    produkAdd.getIdProduk());
        }
        //Penjual
        Notifikasi addNotifikasiPenjual = new Notifikasi(
                "kamu berhasil menambahkan produk " +produk.getNamaProduk(),
                produk.getUser(),
                null
        );
        notifikasiRepository.save(addNotifikasiPenjual);
        return  ResponseEntity.ok().body( new WebResponse<>(
                HttpStatus.OK.value(),
                "OK",
                "Berhasil menambahkan produk baru",
                ""));
    }

    @Override
    public WebResponse<String,?> getListProduk(
            String namaProduk,
            Integer pageNo,
            Integer pageSize,
            Integer idKategori
    ) throws NotFoundException {
        Pageable paging = PageRequest.of(pageNo,pageSize);
        List<ProdukResponse> produkList ;
        Kategori kategori ;
        if (namaProduk == null && idKategori == null){
            produkList = produkRepository.findAll().
                    stream().map(this::convertProdukToProdukResponse).collect(Collectors.toList());
        }else if (idKategori == null) {
            produkList = produkRepository.
                    findByNamaProdukContains(namaProduk).stream().map(this::convertProdukToProdukResponse)
                    .collect(Collectors.toList());
        }else if (namaProduk == null){
            kategori = kategoriRepository.findById(idKategori).orElseThrow(()->new NotFoundException("id kategori tidak ada"));
            produkList = produkRepository.findAllProdukByKategori(kategori).
                    stream().map(this::convertProdukToProdukResponse).collect(Collectors.toList());
        }else {
            kategori = kategoriRepository.findById(idKategori).orElseThrow(()->new NotFoundException("id kategori tidak ada"));
            produkList = produkRepository.findByNamaProdukContainsAndKategori(namaProduk,kategori).stream().map(
                    this::convertProdukToProdukResponse
            ).collect(Collectors.toList());
        }

        int start = (int) paging.getOffset();
        int end = (start + paging.getPageSize()) > produkList.size() ? produkList.size() : (start + paging.getPageSize());

        Page<ProdukResponse> pageResult = new PageImpl<>(
                produkList.subList(start,end),
                paging,
                produkList.size());

        Map<String, Object> response = new HashMap<>();
        response.put("produk", pageResult.getContent());
        response.put("currentPage", pageResult.getNumber());
        response.put("totalItems", pageResult.getTotalElements());
        response.put("totalPages", pageResult.getTotalPages());

        if (pageResult.hasContent()){
            return new WebResponse<>(
                    HttpStatus.OK.value(),
                    "OK",
                    "Berhasil mendapatkan list all produk ",
                    response
            );
        }else {
            return new WebResponse<>(
                    HttpStatus.OK.value(),
                    "OK",
                    "Data produk tidak ada masih kosong ",
                    ""
            );
        }

    }

    @Override
    public ResponseEntity<?> getListProdukWithoutPagination(){
        Stream<ProdukResponse> produkResponse = produkRepository.findAll().stream().map(this::convertProdukToProdukResponse);
        return ResponseEntity.ok().body(produkResponse);
    }

    @Override
    public WebResponse<String,?> getListProdukByPenjualWithoutPagination(int idPenjual) throws NotFoundException {
        Users penjual = userRepository.findById(idPenjual).orElseThrow(
                ()-> new NotFoundException("Id Penjual tidak ada"));
        Stream<ProdukResponse> produkResponses = produkRepository.findAllProdukByPenjual(penjual)
                .stream().map(this::convertProdukToProdukResponse);
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "OK",
                "Berhasil mendapatkan list Produk produk by id penjual",
                produkResponses
        );
    }

    @Override
    public ResponseEntity<?> getProdukByPenjualWishlistWithoutPagination(Integer idPenjual) throws NotFoundException {
        Users penjual =userRepository.findById(idPenjual).orElseThrow(
                ()->new NotFoundException("Id Penjual tidak ada")
        );
        Stream<ProdukResponse> produkResponseStream = produkRepository.findAllProdukByWishlist(penjual).stream().map(
                this::convertProdukToProdukResponse
        );
        return ResponseEntity.ok().body(
                produkResponseStream
        );
    }

    @Override
    public ResponseEntity<?> getProdukByPenjualTransaksiWithoutPagination(Integer idPenjual) throws NotFoundException {
        Users penjual = userRepository.findById(idPenjual).orElseThrow(
                ()->new NotFoundException("Id Penjual tidak ada")
        );
        Stream<ProdukResponse> produkResponseStream = produkRepository.findAllProdukByTransaksi(penjual).stream().map(
                this::convertProdukToProdukResponse
        );
        return ResponseEntity.ok().body(
                produkResponseStream
        );
    }

    @Override
    public ResponseEntity<?> searchProdukByNameWithoutPagination(String nameProduk) throws NotFoundException {
        Stream<ProdukResponse> produkResponseStream = produkRepository.findByNamaProdukContains(nameProduk).stream().map(
                this::convertProdukToProdukResponse
        );
        return ResponseEntity.ok().body(
                produkResponseStream
        );
    }

    @Override
    public ResponseEntity<?> sortProdukByKategoriWithoutPagination(Integer kategori) throws NotFoundException {
        Kategori checkKategori = kategoriRepository.findById(kategori).orElseThrow(
                ()-> new NotFoundException("kategori tidak ada")
        );
        Stream<ProdukResponse> produkResponseStream = produkRepository.findAllProdukByKategori(checkKategori).stream().map(
                this::convertProdukToProdukResponse
        );
        return  ResponseEntity.ok().body(
                produkResponseStream
        );
    }


    @Override
    public ResponseEntity<?> getProdukDetailById(Integer idProduk)
            throws NotFoundException {

        Produk produk = produkRepository.findById(idProduk).orElseThrow(()-> new NotFoundException("idProduk tidak ada"));
        ProdukDetailResponse produkDetailResponse = convertProdukToProdukDetailResponse(produk);
        return ResponseEntity.ok().body(
                new WebResponse<>(
                        HttpStatus.OK.value(),
                        "OK",
                        "Berhasil mendapatkan detail by idProduk",
                        produkDetailResponse
                )
        );

    }


    @Override
    public WebResponse<String,?> deleteProduk(Integer idProduk,
                                              Integer idPenjual) throws NotFoundException {
        Produk produk = produkRepository.findById(idProduk).orElseThrow(
                ()->new NotFoundException("id produk tidak ditemukan"));
        Users userPenjual = userRepository.
                findById(idPenjual).orElseThrow(
                        ()->new NotFoundException("id penjual tidak ditemukan"));

        if (!Objects.equals(produk.getUser().getIdUser(), userPenjual.getIdUser())){
            throw new NotFoundException("id produk dengan id penjual anda tidak ada");
        }

        produkRepository.deleteById(idProduk);
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "OK",
                "Berhasil menghapus produk",
                ""
        );
    }

    @Override
    public WebResponse<String, ?> updateProduk(ProdukUpdateRequest produkUpdateRequest) throws NotFoundException {

        Produk produk = produkRepository.findById(produkUpdateRequest.getIdProduk()).orElseThrow(
                ()->new NotFoundException("id produk tidak ditemukan"));
        Users userPenjual = userRepository.
                findById(produkUpdateRequest.getIdPenjual()).orElseThrow(
                        ()->new NotFoundException("id penjual tidak ditemukan"));

        if (!Objects.equals(produk.getUser().getIdUser(), userPenjual.getIdUser())){
            throw new NotFoundException("id produk dengan id penjual anda tidak ada");
        }else {
            Kategori kategori;
            if (produkUpdateRequest.getIdKategori()!= null){
                kategori= kategoriRepository.findById(produkUpdateRequest.getIdKategori())
                        .orElseThrow(()->new NotFoundException("id kategori tidak ada"));
                produk.setKategori(kategori);
            }

            produk.setNamaProduk(produkUpdateRequest.getNamaProduk());
            produk.setDeskripsiProduk(produkUpdateRequest.getDeskripsiProduk());
            produk.setHargaProduk(produkUpdateRequest.getHargaProduk());
            produkRepository.save(produk);
        }
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "OK",
                "Berhasil update produk",
                ""
        );
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
                produk.getUser().getIdUser(),
                gambarProdukService.getGambarProdukByIdProduk(
                        produk.getIdProduk()
                ).map(String::valueOf).collect(Collectors.toSet())
        );
    }

    private ProdukDetailResponse convertProdukToProdukDetailResponse(Produk produk){
        return new ProdukDetailResponse(
                produk.getIdProduk(),
                produk.isStatusTerhapus(),
                produk.getNamaProduk(),
                produk.isStatusTerjual(),
                produk.getDeskripsiProduk(),
                produk.getHargaProduk(),
                produk.getPotonganDiskon(),
                produk.getKategori(),
                produk.getUser().getIdUser(),
                produk.getUser().getName(),
                convertGambarUserToLinkGambarUser(produk.getUser().getUserDetail()),
                produk.getUser().getUserDetail().getKota(),
                gambarProdukService.getGambarProdukByIdProduk(
                        produk.getIdProduk()
                ).map(String::valueOf).collect(Collectors.toSet())
        );
    }

    private UserGambarLinkResponse convertGambarUserToLinkGambarUser(UserDetails userDetails){
        if (userDetails.getGambarUser() != null ) {
            return new UserGambarLinkResponse(
                    herokuUrlUtil.getUrlApi() + "api/user/" + userDetails.getIdUserDetails() + "/detail/gambar/"
            );
        } else {
            return new UserGambarLinkResponse("");
        }
    }

}