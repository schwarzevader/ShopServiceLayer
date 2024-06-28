package org.example.shopservicelayer;

import org.example.shopservicelayer.repositories.imp.SpecValueRepoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopServiceLayerApplication implements CommandLineRunner {

    @Autowired
    SpecValueRepoImp specValueRepoImp;

    public static void main(String[] args) {
        SpringApplication.run(ShopServiceLayerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        specValueRepoImp.getSpecValueParent(1L).forEach(System.out::println);
    }
}
