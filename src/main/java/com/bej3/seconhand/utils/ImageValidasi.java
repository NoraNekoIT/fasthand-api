package com.bej3.seconhand.utils;

import org.springframework.web.multipart.MultipartFile;

public class ImageValidasi {

    public static boolean validasiImage(MultipartFile file) {
        if ( ("image/jpg".equals(file.getContentType())) || ("image/png".equals(file.getContentType()))
                || ("image/jpeg".equals(file.getContentType()))
        ){
            return true;
        }
        else{
            return false;
        }
    }

}
