package com.bej3.seconhand.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class HerokuUrlUtil {
    @Value("${url.linkApi}")
    private String urlApi;
}