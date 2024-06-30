package org.example.shopservicelayer;

import lombok.extern.slf4j.Slf4j;
import org.example.shopservicelayer.entity.ProductSpecsValue;
import org.example.shopservicelayer.repositories.imp.SpecValueRepoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ShopServiceLayerApplication implements CommandLineRunner {

    @Autowired
    SpecValueRepoImp specValueRepoImp;

    public static void main(String[] args) {
        SpringApplication.run(ShopServiceLayerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {







        log.info("SAVE  children------------------");
//        specValueRepoImp.addChildren(1095L,1049L);
//        specValueRepoImp.addChildren(1096L,1049L);

//        specValueRepoImp.findById(1049L).getChildren().forEach(System.out::println);

        log.info("get  children------------------");
//        specValueRepoImp.getSpecValueParent(1049L).forEach(System.out::println);
        specValueRepoImp.getSpecValueParentt(1049L).forEach(System.out::println);

    }
}
