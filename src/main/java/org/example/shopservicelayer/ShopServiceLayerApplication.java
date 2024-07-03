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







//        log.info("SAVE  children------------------");
//        specValueRepoImp.addChildren(1095L,1049L);
//        specValueRepoImp.addChildren(1096L,1049L);

//        specValueRepoImp.addChildren(1031L,1049L);
//        specValueRepoImp.addChildren(1054L,1031L);
//        specValueRepoImp.addChildren(1055L,1031L);
//        specValueRepoImp.addChildren(1056L,1031L);
//
//
//
//        specValueRepoImp.addChildren(1054L,1049L);
//        specValueRepoImp.addChildren(1089L,1054L);
//        specValueRepoImp.addChildren(1090L,1054L);
//        specValueRepoImp.addChildren(1003L,1054L);
//
//        specValueRepoImp.addChildren(1055L,1049L);
//        specValueRepoImp.addChildren(1007L,1055L);
//        specValueRepoImp.addChildren(1085L,1055L);
//        specValueRepoImp.addChildren(1086L,1055L);
//
//        specValueRepoImp.addChildren(1056L,1049L);
//        specValueRepoImp.addChildren(1009L,1056L);
//        specValueRepoImp.addChildren(1087L,1056L);
//        specValueRepoImp.addChildren(1088L,1056L);


//        specValueRepoImp.addChildren(1006L,1040L);
//        specValueRepoImp.addChildren(1017L,1040L);
//        specValueRepoImp.addChildren(1020L,1040L);
//
//        specValueRepoImp.addChildren(1000L,1006L);
//        specValueRepoImp.addChildren(1019L,1006L);







//        specValueRepoImp.getSpecValueParent(1049L).forEach(System.out::println);
        log.info("get  children by------------------1006");
//        specValueRepoImp.getSpecValueParentt(1049L).forEach(System.out::println);

        specValueRepoImp.getSpecsAndValues(1006L).forEach(System.out::println);
//        specValueRepoImp.getSpecValueParentt(1049L);

        log.info("get  children by------------------1049");
        specValueRepoImp.getSpecsAndValues(1020L).forEach(System.out::println);

    }
}
