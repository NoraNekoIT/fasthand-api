package com.bej3.seconhand.payloads.requests;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
@Getter
@Setter
public class ProdukAddRequest {
    @NotEmpty(message = "nama produk tidak boleh empty")
    @NotBlank(message = "nama produk tidak boleh blank")
    private String namaProduk;
    @NotBlank(message = "deskripsi produk tidak boleh blank")
    @NotEmpty(message = "deskritpsi produk tidak boleh empty")
    private String deskripsiProduk;
    @NotNull(message = "hargaProduk tidak boleh null")
    private double hargaProduk;
    @NotNull(message = "idKategori tidak boleh null")
    private Integer idKategori;
    @NotNull(message = "idPenjual tidak boleh null")
    private Integer idPenjual;
    @NotNull(message = "isi gambar minimal 1 gambar max 5")
    private List<MultipartFile> file;
    //    private double potonganDiskon;
}