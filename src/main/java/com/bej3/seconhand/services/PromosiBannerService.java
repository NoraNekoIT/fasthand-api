package com.bej3.seconhand.services;

import com.bej3.seconhand.entities.PromosiBanner;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.requests.PromosiBannerRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PromosiBannerService {

    String uploadPromosiBanner(MultipartFile file, int idPromosi) throws IOException;

    PromosiBanner getPromosiBanner(int id) throws NotFoundException;

    void promosiBannerLabel(PromosiBannerRequest promosiBannerRequest);
}