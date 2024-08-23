package org.example.shopservicelayer.repositories.jpaInterfaces;




import org.example.shopservicelayer.dto.ProductDTO;
import org.example.shopservicelayer.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {



    Optional<Product> findById(Long id);



    @Query(value = """
            select  new org.example.shopservicelayer.dto.ProductDTO(
                p.id ,
                p.name ,
                p.description,
                p.price,
                 p.rating,
                 p.shadowRating )
            from  products p
            join p.productCategory  pc
            where pc.id =:idC
             """)
    Page<ProductDTO> getProductsByProductCategoryId(@Param("idC") Long id , Pageable pageable);


    @Query(value = """
            select  new org.example.shopservicelayer.dto.ProductDTO(
                p.id ,
                p.name ,
                p.description,
                p.price,
                 p.rating,
                 p.shadowRating )
            from  products p
            where p.id in:idL
             """)
    Page<ProductDTO> compareProductsByProductsListID(@Param("idL") List<Long> idList , Pageable pageable);

    Product findProductByName(String name);

    Product findProductById(Long id);

    boolean existsProductById(Long id);




}
