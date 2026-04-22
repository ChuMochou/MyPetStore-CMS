package org.csu.petstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.csu.petstore"})
@MapperScan("org.csu.petstore.persistence")
public class MyPetStoreCMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyPetStoreCMSApplication.class, args);
    }
}