package com.bej3.seconhand;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class PasswordGeneratorTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void generatePassword(){
        log.info("{}",passwordEncoder.encode("admin"));
    }

}