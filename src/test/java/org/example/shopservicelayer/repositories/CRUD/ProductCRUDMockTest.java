package org.example.shopservicelayer.repositories.CRUD;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;


import org.example.shopservicelayer.ContainersConfig;
import org.example.shopservicelayer.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@SpringBootTest(classes = ContainersConfig.class)
//@AutoConfigureMockMvc(printOnlyOnFailure = false)
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ContainersConfig.class)
@AutoConfigureMockMvc

public class ProductCRUDMockTest  {
    @Autowired
    private MockMvc mockMvc;


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    ProductCRUD productCRUD;

    @Autowired
    private ObjectMapper objectMapper;



    String fromProductToJSON(Product p) {
        try {
            return objectMapper.writeValueAsString(p);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    Product createProd(){
        Product product = new Product();
        product.setDescription("some product description");
        product.setRating(3);
        product.setId(1L);
        product.setShadowRating(2);
        product.setName(" some product name");
        product.setPrice(500);
        product.setQuantity(100);
//        product.setProductCategory();
//        product.setProductSpecItemList();
        return product;
    }

    @Test
    public void testCreateProduct() throws Exception {
        String newProductJson = "{\"name\": \"Test Product\", \"price\": 20.0}";


        Product product = new Product();
        product.setDescription("some product description");
        product.setRating(3);
        product.setId(1L);
        product.setShadowRating(2);
        product.setName(" some product name");
        product.setPrice(500);
        product.setQuantity(100);
//        product.setProductCategory();
//        product.setProductSpecItemList();


//        mockMvc.perform(MockMvcRequestBuilders.post("/productCRUD")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(String.valueOf(product)))
//                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateProduct() throws Exception {
        long productId = 1000L;

//        Product testProd = createProd();
//        long productId = testProd.getId();
        String newProdDesc = "new product description";


        Product product = productCRUD.getReferenceById(productId);
        product.setDescription(newProdDesc);


        String updatedProductJson = "{\"id\": " + productId + ", \"name\": \"Updated Product\", \"price\": 30.0}";

//        mockMvc.perform(MockMvcRequestBuilders.put("/productCRUD/" + productId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(fromProductToJSON(product)))
//                .andExpect(status().isOk());

        HttpEntity<Product> updateEntity = new HttpEntity<>(product);
        ResponseEntity<Product> updateResponse = restTemplate.exchange(
                getBaseUrl() + "/" + productId,
                HttpMethod.PUT,
                updateEntity,
                Product.class
        );

        assertEquals(newProdDesc,productCRUD.getReferenceById(productId).getDescription());
    }

    @Test
    public void testDeleteProduct() throws Exception {
        long productId = 1L;
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/productCRUD/" + productId))
//                .andExpect(status().isNoContent());
    }

    private String getBaseUrl() {
        return "http://localhost:" + port + "/productCRUD";
    }
}
