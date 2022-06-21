package com.bej3.seconhand;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com/bej3/seconhand/entities"})
@OpenAPIDefinition(
        info = @Info(title = "API Fasthand",
                description = "STAGING VERSION -> API FASTHAND API BY BEJ-3 KELOMPOK 2",
                version = "v1.0.0",
                contact = @Contact(
                        name = "",
                        url = "",
                        email = ""

                ),
                license = @License(
                        name = "",
                        url = ""
                ),
                termsOfService =""
        )
)
public class SeconhandApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeconhandApplication.class, args);
    }
}