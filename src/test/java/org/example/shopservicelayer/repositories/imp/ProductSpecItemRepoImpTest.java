package org.example.shopservicelayer.repositories.imp;

import com.github.dockerjava.api.model.ContainerConfig;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import org.example.shopservicelayer.ContainersConfig;
import org.example.shopservicelayer.repositories.ProductRepository;
import org.example.shopservicelayer.repositories.ProductSpecItemRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;


@AutoConfigureMockMvc(printOnlyOnFailure = false)
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = ContainerConfig.class)
//@SpringBootTest
@Transactional
class ProductSpecItemRepoImpTest  {

    @Autowired
    ProductSpecItemRepoImp productSpecItemRepoImp;

    @Autowired
    ProductSpecItemRepo p;

    ProductRepository prod;

    @Test
    void pageTest(){



//                .map(t -> {
//                    Specs specs = new Specs();
//                    specs.setId(t.getId());
//                    specs.setName(t.getName());
//                    specs.setValues(t.getValues().stream()
//                            .map(svp -> new SpecValue(svp.getSpecValue(), svp.getId(), svp.getSpecName()))
//                            .collect(Collectors.toList()));
//                    return specs;
//                })
//                .toList();
        Pageable pageProd =  PageRequest.of(0,100);

//        Pageable page1 = PageRequest.of(0, 9);
//        p.getProductByIdIs(List.of(1017L,1039L),page1).stream().map(t->{
//            return new ProdDTO(t.getId(),t.getName(),t.getDescription(),t.getPrice(),t.getRating(),t.getShadowRating(),
//                    t.getSpecItemList().stream().map(list->new SpecsItemsDTO(list.getId(),list.getQuantity())).collect(Collectors.toList()));
//        }).forEach(System.out::println);


//        log.info(prod.findById(1000L).orElseThrow().toString());
//        log.info(prod.findProductById(1000L).toString());
//        log.info("log-----------------------"+p.getProductDTOBySpecValueIDS(List.of(1008L),  pageProd).getContent().size());
//        log.info("log-----------------------"+p.findAll().size());
    }
    @Test
    void deleteFromImp(){
        Pageable page =  PageRequest.of(0,100);
        System.out.println("total pages"+p.getProductDTOBySpecValueIDSd(List.of(1008L,1018L),page).getTotalPages());

        productSpecItemRepoImp.delete(1L,1L);

    }


    @Test
    void delete(){

        log.info(p.findProductSpecItem(1000L,1008L).toString());

//        p.deleteProductSpecItem(1000L,1008L);
//
//        log.info(p.findProductSpecItem(1000L,1008L).orElse(new ProductSpecItem()).toString());
    }

}