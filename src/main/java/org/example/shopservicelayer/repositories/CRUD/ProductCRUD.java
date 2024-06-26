package org.example.shopservicelayer.repositories.CRUD;



import org.example.shopservicelayer.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


//@RepositoryRestResource(path = "productCRUD")
public interface ProductCRUD extends JpaRepository<Product,Long> {


//    @EntityGraph(attributePaths = "products")
//    Page<ProductProjection>  getProdu( List<Long> valuesList
//            , Pageable pageable);
//
//
//    @EntityGraph(attributePaths = "products")
//    Page<ProductProjection> getProductDTOByProductSpecsValueID(@Param("values") List<Long> valuesList
//            , Pageable pageable);

    Product findProductById(Long aLong);
//    Optional<Product> findProductById(Long aLong);
}
