package com.bej3.seconhand.services.impls;

import com.bej3.seconhand.entities.GambarProduk;
import com.bej3.seconhand.entities.Produk;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.responses.GambarProdukLinkResponse;
import com.bej3.seconhand.repositories.GambarProdukRepository;
import com.bej3.seconhand.repositories.ProdukRepository;
import com.bej3.seconhand.services.GambarProdukService;
import com.bej3.seconhand.util.HerokuUrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class GambarProdukServiceImpl implements GambarProdukService{
    private final GambarProdukRepository gambarProdukRepository;
    private final ProdukRepository produkRepository;
    private final HerokuUrlUtil herokuUrlUtil;

    @Autowired
    public GambarProdukServiceImpl(GambarProdukRepository gambarProdukRepository,
                                   ProdukRepository produkRepository,
                                   HerokuUrlUtil herokuUrlUtil
                                   ) {
        this.gambarProdukRepository = gambarProdukRepository;
        this.produkRepository = produkRepository;
        this.herokuUrlUtil =herokuUrlUtil;
    }

    @Override
    public String uploadGambarProduk(MultipartFile file, int idProduk) throws IOException {
        GambarProduk gambarProduk = new GambarProduk(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes()
        );
        gambarProdukRepository.save(gambarProduk);
        return "sukses upload gambar " + file.getOriginalFilename();
    }

    @Override
    public GambarProduk getGambarProduk(int id) throws NotFoundException {
        return gambarProdukRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Stream<GambarProdukLinkResponse> getGambarProdukByIdProduk(int idProduk) {
        Optional<Produk> produk = produkRepository.findById(idProduk);
        return gambarProdukRepository.findAllGambarProdukByProduk(produk).stream().map(this::convertGambarProdukToLink);
    }

    private GambarProdukLinkResponse  convertGambarProdukToLink(GambarProduk gambarProduk){
        return new GambarProdukLinkResponse(
                herokuUrlUtil.getUrlApi() + "api/gambarProduk/"+gambarProduk.getIdGambarProduk()
        );
    }

}