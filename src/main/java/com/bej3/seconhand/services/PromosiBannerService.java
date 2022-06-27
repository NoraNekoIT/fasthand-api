package com.bej3.seconhand.services;

import com.bej3.seconhand.entities.PromosiBanner;
import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.responses.WebResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PromosiBannerService {

    WebResponse<String,?> uploadPromosiBanner(MultipartFile file,
                               String labelPromosi) throws IOException;

    PromosiBanner getPromosiBanner(int id) throws NotFoundException;

    WebResponse<String,?> getAllPromosiBanner();

//    void promosiBannerLabel(PromosiBannerRequest promosiBannerRequest);
}