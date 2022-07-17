package com.bej3.seconhand.services.impls;

import com.bej3.seconhand.entities.GambarProduk;
import com.bej3.seconhand.entities.Produk;
import com.bej3.seconhand.entities.PromosiBanner;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.PromosiBannerRequest;
import com.bej3.seconhand.payloads.responses.GambarProdukLinkResponse;
import com.bej3.seconhand.payloads.responses.PromosiBannerResponse;
import com.bej3.seconhand.payloads.responses.WebResponse;
import com.bej3.seconhand.repositories.PromosiBannerRepository;
import com.bej3.seconhand.services.PromosiBannerService;
import com.bej3.seconhand.utils.HerokuUrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PromosiBannerServiceImpl implements PromosiBannerService {

    private final PromosiBannerRepository promosiBannerRepository;
    private final HerokuUrlUtil herokuUrlUtil;

    @Autowired
    public PromosiBannerServiceImpl(PromosiBannerRepository promosiBannerRepository,
                                    HerokuUrlUtil herokuUrlUtil
    ) {
        this.promosiBannerRepository = promosiBannerRepository;
        this.herokuUrlUtil = herokuUrlUtil;
    }

    @Override
    public WebResponse<String,?>  uploadPromosiBanner(MultipartFile file,
                                      String labelPromosi
                                      ) throws IOException {
        try{
            PromosiBanner promosiBanner = new PromosiBanner(
                    file.getBytes(),
                    file.getContentType(),
                    labelPromosi
            );
            promosiBannerRepository.save(promosiBanner);
        }
        catch (
                Exception e
        ){
            e.printStackTrace();
        }
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "OK",
                "Berhasil Upload Promosi Banner",
                ""
        );
    }

    @Override
    public PromosiBanner getPromosiBanner(int id) throws NotFoundException {
        return promosiBannerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("id gambar promosi banner tidak ditemukan"));
    }

    @Override
    public WebResponse<String, ?> getAllPromosiBanner() {
        Stream<PromosiBannerResponse> promosiBannerStream =
                promosiBannerRepository.findAll().stream()
                        .map(this::convertPromosiBannerToPromosiBannerResponse);

        return new WebResponse<>(
                HttpStatus.OK.value(),
                "OK",
                "Berhasil mendapatkan list promosi",
                promosiBannerStream
        );
    }

//    @Override
//    public void promosiBannerLabel(PromosiBannerRequest promosiBannerRequest) {
//        PromosiBanner promosiBannerLabel = new PromosiBanner();
//        promosiBannerRequest.getIdPromosi();
//        promosiBannerRequest.getGambarBanner();
//        promosiBannerRequest.getLabelPromosi();
//        promosiBannerRepository.save(promosiBannerLabel);
//    }

    private String convertGambarPromosiBannerToLink(PromosiBanner promosiBanner) {
        return herokuUrlUtil.getUrlApi() + "api/promosiBanner/" + promosiBanner.getIdPromosi() + "/gambar";
    }

    private PromosiBannerResponse convertPromosiBannerToPromosiBannerResponse(PromosiBanner promosiBanner) {
        return new PromosiBannerResponse(
                promosiBanner.getIdPromosi(),
                promosiBanner.getLabelPromosi(),
                convertGambarPromosiBannerToLink(promosiBanner)
        );
    }
}