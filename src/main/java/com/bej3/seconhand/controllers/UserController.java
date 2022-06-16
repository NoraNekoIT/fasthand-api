package com.bej3.seconhand.controllers;

import com.bej3.seconhand.payloads.responses.WebResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/user")
    public WebResponse<String> coba(){
        return new WebResponse<>(
                HttpStatus.OK.value(),
                "BERHASIL",
                "COBA DATA"
        );
    }

}
