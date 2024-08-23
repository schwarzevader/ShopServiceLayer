package org.example.shopservicelayer.repositories.jpaInterfaces;


import org.example.shopservicelayer.entity.ProductSpecName;
import org.example.shopservicelayer.projection.SpecsProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;




public interface ProductSpecsNameRepo extends JpaRepository<ProductSpecName, Long>  {

//////////////////////////////

//    @EntityGraph(attributePaths = "productSpecValues")


//    @Query("select distinct pSN.id as id ," +
//           " pSN.name as name ," +
//           " pSN.productSpecValues as productSpecValues " +
//           "from product_spec_name pSN " +
////           "left join pSN.productSpecValues v on v.productSpecName.id=pSN.id " +
////           "left join pSN.productCategory pC on pC.id=pSN.productCategory.id " +
//           "left join pSN.productSpecValues v on pSN.id = v.productSpecName.id " +
//           "left join pSN.productCategory pC on pSN.productCategory.id = pC.id " +
//           "where pC.id = :categoryId")
//    List<SpecsProjection> findByProductCategory(@Param("categoryId") Long categoryId);


//    @EntityGraph(attributePaths = "productSpecValues")
//    List<SpecsProjection> findByProductCategoryId( Long categoryId);


    @EntityGraph(attributePaths = "productSpecValues")
    List<SpecsProjection> findByProductCategoryId(Long categoryId, Pageable page1);


}
