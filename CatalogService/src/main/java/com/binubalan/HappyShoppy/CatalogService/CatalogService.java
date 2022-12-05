package com.binubalan.HappyShoppy.CatalogService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogService {
    public static void main(String[] args) {
        System.out.println("Starting catalog spring service");
        SpringApplication.run(CatalogService.class);
    }
}
