package org.example.shopservicelayer;

import org.springframework.boot.SpringApplication;

public class TestShopServiceLayerApplication {

    public static void main(String[] args) {
        SpringApplication.from(ShopServiceLayerApplication::main).with(ContainersConfig.class).run(args);
    }

}
