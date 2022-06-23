package com.bej3.seconhand.services.impls;

import com.bej3.seconhand.entities.GambarProduk;
import com.bej3.seconhand.entities.PromosiBanner;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.PromosiBannerRequest;
import com.bej3.seconhand.repositories.PromosiBannerRepository;
import com.bej3.seconhand.services.PromosiBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PromosiBannerServiceImpl implements PromosiBannerService {

    private final PromosiBannerRepository promosiBannerRepository;

    @Autowired
    public PromosiBannerServiceImpl(PromosiBannerRepository promosiBannerRepository){
        this.promosiBannerRepository = promosiBannerRepository;
    }

    @Override
    public String uploadPromosiBanner(MultipartFile file, int idPromosi) throws IOException {
        try{
            PromosiBanner promosiBanner = new PromosiBanner(
                    file.getOriginalFilename(),
                    file.getBytes()
            );
            promosiBannerRepository.save(promosiBanner);
        }
        catch (
                Exception e
        ){
            e.printStackTrace();
        }
        return "sukses upload gambar" + file.getOriginalFilename();
    }

    @Override
    public PromosiBanner getPromosiBanner(int id) throws NotFoundException {
        PromosiBanner promosiBanner = promosiBannerRepository.findById(id).orElseThrow(NotFoundException::new);
        return promosiBanner;
    }

    @Override
    public void promosiBannerLabel(PromosiBannerRequest promosiBannerRequest) {
        PromosiBanner promosiBannerLabel = new PromosiBanner();
        promosiBannerRequest.getIdPromosi();
        promosiBannerRequest.getGambarBanner();
        promosiBannerRequest.getLabelPromosi();
        promosiBannerRepository.save(promosiBannerLabel);
    }
}