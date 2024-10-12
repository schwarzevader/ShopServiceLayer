package org.example.shopservicelayer.repositories.repositoriesInterfaces;

import org.example.shopservicelayer.dto.ProductUpdateDto;
import org.example.shopservicelayer.entity.Product;

public interface ProductCrudRepository {
    Product save(Product product);
    Product update(ProductUpdateDto productUpdateDto);
    Product findProductById(Long id);
    void  deleteProductById(Long id);
}
