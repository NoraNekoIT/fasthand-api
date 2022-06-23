package com.bej3.seconhand;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com/bej3/seconhand/entities"})
@OpenAPIDefinition(
        info = @Info(title = "API Fasthand",
                description = "API FASTHAND API BY BEJ-3 KELOMPOK 2 ",
                version = "v1.0.0",
                contact = @Contact(
                        name = "API SUPPORT",
                        url = "",
                        email = ""

                ),
                license = @License(
                        name = "",
                        url = ""
                ),
                termsOfService = ""),
        servers = {
                @Server(
                        url = "https://staging-fasthand-api.herokuapp.com/",
                        description = "The Staging API Server"),
                @Server(
                        url ="https://production-fasthand-api.herokuapp.com/",
                        description = "The Production API Server"
                ),
                @Server(
                        url = "http://localhost:8089/",
                        description = "The Lokal Development API Server"
                )
        },
        security = @SecurityRequirement(
                name = ""
        )
)

public class SeconhandApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeconhandApplication.class, args);
    }
}