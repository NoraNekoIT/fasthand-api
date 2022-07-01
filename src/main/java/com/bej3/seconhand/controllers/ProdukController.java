package com.bej3.seconhand.controllers;

import com.bej3.seconhand.entities.Kategori;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.ProdukAddRequest;
import com.bej3.seconhand.payloads.requests.ProdukUpdateRequest;
import com.bej3.seconhand.payloads.requests.UserUpdateRequest;
import com.bej3.seconhand.payloads.responses.WebResponse;
import com.bej3.seconhand.services.ProdukService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/produk")
public class ProdukController {
    private final ProdukService produkService;

    @Autowired
    public ProdukController(ProdukService produkService) {
        this.produkService = produkService;
    }

    @GetMapping("/all")
    @Operation(
            description = "mendapatkan semua list produk, " +
                    "searchNamaKota sebagai searching berdasarkan nama produk, " +
                    "pageNo sebagai no dari halaman, " +
                    "pageSize sebagai jumlah kota yang ingin ditampilkan, " +
                    "sortByIdKategori sorting berdasarkan id kategori misal bisa diisi idKategori 1, " +
                    "searchNamaProduk dan sortByIdKategor bisa dikosongkan atau isi salah satu karena tidak wajib diisi"
    )
    public WebResponse<String,?> getListProduk(
            @RequestParam(required = false) String searchNamaProduk,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer sortByIdKategori
            ) throws NotFoundException {
        return produkService.getListProduk(searchNamaProduk,
                pageNo,
                pageSize,
                sortByIdKategori
                );
    }

    @GetMapping("/penjual/{idPenjual}")
    @PreAuthorize("hasRole('SELLER')")
    @Operation(description = "untuk mendapatkan beranda get list berdasarkan id penjual",
            security = @SecurityRequirement(
                    name = "bearerAuth"
            )
    )
    public WebResponse<String,?> getListProdukByPenjual(@PathVariable int idPenjual)
            throws NotFoundException {
        return produkService.getListProdukByPenjual(idPenjual);
    }

//    @GetMapping("/all/kategori/{idKategori}")
//    public WebResponse<String,?> getListProdukByKategori(@PathVariable int idKategori) throws NotFoundException {
//      return produkService.getListProdukByKategori(idKategori);
//    }

    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasRole('SELLER')")
    @Operation(description = "untuk add produk upload gambar max 5 dan jumlah postingan produk max 4",
            security = @SecurityRequirement(
                    name = "bearerAuth"
            )
    )
    public ResponseEntity<?> insertProduk(@Valid @ModelAttribute ProdukAddRequest produkAddRequest)
            throws NotFoundException, IOException {
        return produkService.addProduk(produkAddRequest);

    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('SELLER')")
    @Operation(description = "untuk menghapus produk dengan safe delete",
            security = @SecurityRequirement(
                    name = "bearerAuth"
            )
    )
    public WebResponse<String,?> deleteProduk(@RequestParam Integer idProduk,
                                              @RequestParam Integer idPenjual
                                              ) throws NotFoundException {
        return produkService.deleteProduk(idProduk,idPenjual);
    }

    @RequestMapping(value = "/update",
            method = RequestMethod.PUT,
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(description = "untuk update produk", security = @SecurityRequirement(
            name = "bearerAuth"
    ))
    @PreAuthorize("hasRole('SELLER')")
    public WebResponse<String, ?> updateProduk(@Valid @ModelAttribute ProdukUpdateRequest produkUpdateRequest)
            throws NotFoundException, IOException {
        return produkService.updateProduk(produkUpdateRequest);
    }

}