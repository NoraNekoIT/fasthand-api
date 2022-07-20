package com.bej3.seconhand.utils;

import org.springframework.web.multipart.MultipartFile;

public class ImageValidasi {

    public static boolean validasiImage(MultipartFile file){
        if ("image/jpg".equals(file.getContentType())){
            return true;
        }else if (("image/png".equals(file.getContentType()))){
            return true;
        }else if (("image/jpeg".equals(file.getContentType()))){
            return true;
        }
        else {
            return false;
        }
    }

}
