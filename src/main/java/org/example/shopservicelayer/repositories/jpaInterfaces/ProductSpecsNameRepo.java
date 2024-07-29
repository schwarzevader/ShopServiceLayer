package org.example.shopservicelayer.repositories.jpaInterfaces;


import org.example.shopservicelayer.entity.ProductSpecName;
import org.example.shopservicelayer.projection.SpecsProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;




public interface ProductSpecsNameRepo extends JpaRepository<ProductSpecName, Long>  {




//    select psn1_0.product_spec_name_id,
//    psn1_0.spec_name,
//    psn1_0.product_category_id,
//    psv1_0.product_spec_name_id,
//    psv1_0.product_spec_value_id,
//    psv1_0.value
//    from product_spec_names psn1_0
//    left join product_categories pc1_0 on pc1_0.product_category_id=psn1_0.product_category_id
//    left join product_spec_value psv1_0 on psn1_0.product_spec_name_id=psv1_0.product_spec_name_id
//    where pc1_0.product_category_id=?






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



////////////////////////////////////////////////////////////

//    @Query("select pSN.id  ," +
//           " pSN.name ," +
//           " pSN.productSpecValues " +
//           "from product_spec_name pSN " +
//
//           "join pSN.productSpecValues v " +
//           "join v.value " +
//           "join v.id " +
//           "join pSN.productCategory pC " +
//           "where pC.id = :categoryId")
//    List<SpecsProjection> findByProductCategoryId(@Param("categoryId") Long categoryId);
}
