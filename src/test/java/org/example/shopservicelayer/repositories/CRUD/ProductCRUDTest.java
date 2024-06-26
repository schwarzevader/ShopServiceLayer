package org.example.shopservicelayer.repositories.CRUD;

import com.github.dockerjava.api.model.ContainerConfig;
import lombok.extern.slf4j.Slf4j;

import org.example.shopservicelayer.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


//@SpringBootTest(classes = TestcontainersConfig.class)
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = ContainerConfig.class)
class ProductCRUDTest   {

//    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Autowired
    ProductCRUD productCRUD;

    @Test
    public void save(){

        Product product = new Product();
        product.setId(1L);
        product.setDescription("");
        product.setPrice(3);
        product.setName("");
        product.setRating(5);
        product.setShadowRating(5);

        productCRUD.save(product);
        log.info(productCRUD.getReferenceById(1L).toString());
    }


    @Test
    public void testCreat(){
        Product product = new Product();
        product.setId(1L);
        product.setDescription("");
        product.setPrice(3);
        product.setName("");
        product.setRating(5);
        product.setShadowRating(5);

        ResponseEntity<Product> createResponse = restTemplate.postForEntity(getBaseUrl(), product, Product.class);
        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        assertNotNull(createResponse.getBody());

        Product createdProduct = createResponse.getBody();
        Long productId = createdProduct.getId();
//        assertNotNull(productId);
        assertEquals(product.getId(),productId);
    }
    @Test
    public void testRead(){
        log.info(productCRUD.findProductById(1000L).toString());
    }
    @Test
    public void testUpdate(){

    }
    @Test
    public void testDelete(){

    }
    @Test
    public void testCRUDOperations() {
        // Create
        Product product = new Product(/* initialize product for creation */);
        ResponseEntity<Product> createResponse = restTemplate.postForEntity(getBaseUrl(), product, Product.class);
        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        assertNotNull(createResponse.getBody());

        Product createdProduct = createResponse.getBody();
        Long productId = createdProduct.getId();
        assertNotNull(productId);

        // Read
        ResponseEntity<Product> readResponse = restTemplate.getForEntity(getBaseUrl() + "/" + productId, Product.class);
        assertEquals(HttpStatus.OK, readResponse.getStatusCode());
        assertEquals(createdProduct, readResponse.getBody());

        // Update
        createdProduct.setName("Updated Name");
        HttpEntity<Product> updateEntity = new HttpEntity<>(createdProduct);
        ResponseEntity<Product> updateResponse = restTemplate.exchange(
                getBaseUrl() + "/" + productId,
                HttpMethod.PUT,
                updateEntity,
                Product.class
        );
        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
        assertEquals(createdProduct, updateResponse.getBody());

        // Delete
        restTemplate.delete(getBaseUrl() + "/" + productId);
        ResponseEntity<Product> deleteResponse = restTemplate.getForEntity(getBaseUrl() + "/" + productId, Product.class);
        assertEquals(HttpStatus.NOT_FOUND, deleteResponse.getStatusCode());
    }

    private String getBaseUrl() {
        return "http://localhost:" + port + "/productCRUD";
    }
}