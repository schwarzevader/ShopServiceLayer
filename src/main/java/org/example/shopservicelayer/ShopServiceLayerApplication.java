package org.example.shopservicelayer;

import lombok.extern.slf4j.Slf4j;
import org.example.shopservicelayer.repositories.imp.CategoryRepositoryImp;
import org.example.shopservicelayer.repositories.imp.ProductRepositoryImp;
import org.example.shopservicelayer.repositories.imp.SpecValueRepoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@Slf4j
public class ShopServiceLayerApplication implements CommandLineRunner {

    @Autowired
    SpecValueRepoImp specValueRepoImp;
    @Autowired
    ProductRepositoryImp productRepositoryImp;

    @Autowired
    CategoryRepositoryImp categoryRepositoryImp;

    public static void main(String[] args) {
        SpringApplication.run(ShopServiceLayerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


//        specValueRepoImp.add(1031L,1049L);
//        specValueRepoImp.add(1054L,1031L);
//        specValueRepoImp.add(1055L,1031L);
//        specValueRepoImp.add(1056L,1031L);
//
//        specValueRepoImp.add(1003L,1054L);
//        specValueRepoImp.add(1089L,1054L);
//        specValueRepoImp.add(1090L,1054L);
//
//        specValueRepoImp.add(1007L,1055L);
//        specValueRepoImp.add(1085L,1055L);
//        specValueRepoImp.add(1086L,1055L);
//
//        specValueRepoImp.add(1009L,1056L);
//        specValueRepoImp.add(1087L,1056L);
//        specValueRepoImp.add(1088L,1056L);


//        System.out.println("---------------------------- getParents 1088");
//        specValueRepoImp.getSpecsParentsAndValues(1088L);
//
//        System.out.println("---------------------------- getChildrens 1049");
//        specValueRepoImp.getSpecsChildrensValues(1049L);

//        System.out.println("---------------------------- getParents 1031");
//        specValueRepoImp.getSpecsParentsAndChildrensValues(1088L);
//        specValueRepoImp.getSpecsParentsAndChildrensValues(List.of(1031L));

//        System.out.println("---------------------------- getChildrens 1049,1031");
//        specValueRepoImp.getSpecsParentsAndChildrensValues(List.of(1049L,1031L)).forEach(System.out::println);

//        System.out.println(productRepositoryImp.getProductAndSpecs(1000L).toString());
//        System.out.println(productRepositoryImp.getProduct(1000L).getProductCategory().getId());

//        System.out.println(productRepositoryImp.getProduct(1000L).getProductSpecItemList().toString());

//        categoryRepositoryImp.getAllCategories().forEach(c-> System.out.println(c.getId()));
//        categoryRepositoryImp.getAllCategories().forEach(System.out::println);
//        categoryRepositoryImp.getAllCategories();
//        System.out.println(categoryRepositoryImp.getAllCategories().stream().filter(k-> k.getId().equals(1000L)).toString());
//        categoryRepositoryImp.getAllCategories().stream().filter(k-> k.getId().equals(2000L)).forEach(System.out::println);

//        categoryRepositoryImp.getAllCategories(true).forEach(System.out::println);

        categoryRepositoryImp.getAllCategories(true);
        categoryRepositoryImp.getAllCategories(true);
        categoryRepositoryImp.getAllCategories(true);

    }
}
