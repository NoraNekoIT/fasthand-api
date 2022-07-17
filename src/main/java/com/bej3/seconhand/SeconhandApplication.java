package com.bej3.seconhand;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com/bej3/seconhand/entities"})
@OpenAPIDefinition(
        info = @Info(title = "API Fasthand",
                description = "# API FASTHAND API BY BEJ-3 KELOMPOK 2 , \n" +
                        "## INGAT PILIH DULU SERVERS berdasarkan domain karena nanti kalau gak sesuai kena cors \n" +
                        "## Ada 3 server development, staging, production. \n" +
                        "### Development -> buat bikin api dari backend , \n" +
                        "### Staging -> digunakan untuk coba/test backend dan fronted jika frontend setuju baru naik ke Production," +
                        "hal ini bertujuan untuk mendapatkan feedback dari frontend \n" +
                        "### Production -> api yang siap di integrasikan di frontend \n",
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
                termsOfService = "")
        , servers = {
        @Server(
                url = "http://localhost:8089/",
                description = "The Development Lokal API Server"
        ),
        @Server(
                url = "https://staging-fasthand-api.herokuapp.com/",
                description = "The Staging API Server"),
        @Server(
                url = "https://production-fasthand-api.herokuapp.com/",
                description = "The Production API Server")

        }
//        ,security = @SecurityRequirement(
//                name = "bearerAuth"
//        )
)
@SecurityScheme(name = "bearerAuth",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
public class SeconhandApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeconhandApplication.class, args);
    }
}