package org.example.shopservicelayer.repositories.jpaInterfaces;



import org.example.shopservicelayer.dto.CategoryDTO;
import org.example.shopservicelayer.dto.ProductDTO;
import org.example.shopservicelayer.entity.ProductCategory;
import org.example.shopservicelayer.projection.SpecsProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> {


    @Query("select pSN.id ," +
           " pSN.name ," +
           " pSV.value , " +
           "pSN.name " +
           "from product_spec_name pSN " +
           "join pSN.productSpecValues pSV " +
           "join pSN.productCategory pC " +
           "where pC.id = :categoryId")
    List<SpecsProjection> getProductSpecsNamesDTOList(@Param("categoryId") Long categoryId);





    @Query(value = """
            select  new org.example.shopservicelayer.dto.ProductDTO(
                p.id ,
                p.name ,
                p.description,
                p.price,
                 p.rating,
                 p.shadowRating
                 )
            from  product_category pC
            join pC.categoryItems  p
            where pC.id  =:categoryId
             """)
    Page<ProductDTO> getProducts(@Param("categoryId") Long CategoryId, Pageable pageable);


    @Query(value = """
              select new org.example.shopservicelayer.dto.CategoryDTO(
              pC.id,
              pC.nameOfCategory)
              from product_category pC
            """)
    List<CategoryDTO> getAllCategoryDTO();

//    @Query(value = "select c.nameOfCategory from ProductCategory c join c.typeCategory t where t.nameOfTypeCategory like:name")
//    public List<String> getCategoriesNameByTypeCategory(@Param("name") String typeCategory);
//
//    @Query(value = "select c.nameOfCategory from ProductCategory c ")
//    public List<String> getCategoriesName(@Param("name") String typeCategory);
}
