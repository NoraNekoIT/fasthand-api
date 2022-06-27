package com.bej3.seconhand.services;

import com.bej3.seconhand.entities.PromosiBanner;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.responses.WebResponse;

public interface PromosiBannerService {

//    String uploadPromosiBanner(MultipartFile file, int idPromosi) throws IOException;

    PromosiBanner getPromosiBanner(int id) throws NotFoundException;

    WebResponse<String,?> getAllPromosiBanner();

//    void promosiBannerLabel(PromosiBannerRequest promosiBannerRequest);
}