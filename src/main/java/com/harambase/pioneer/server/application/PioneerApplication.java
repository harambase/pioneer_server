package com.harambase.pioneer.server.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableTransactionManagement
@EnableCaching
@SpringBootApplication
@EnableSwagger2
public class PioneerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PioneerApplication.class, args);
    }
}
