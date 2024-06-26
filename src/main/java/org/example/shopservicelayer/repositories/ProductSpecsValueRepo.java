package org.example.shopservicelayer.repositories;



import org.example.shopservicelayer.entity.ProductSpecsValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductSpecsValueRepo extends JpaRepository<ProductSpecsValue, Long> {



    @Query(value = "select psv from  productSpecValue  psv " +
                   "where psv.valueId in :ids")
    List<ProductSpecsValue> getProductSpecsValueByValueIdIs(@Param("ids")List<Long> ids);

//    @EntityGraph(attributePaths = "products")
//    Page<ProductProjection> findProductSpecsValueIn(List<Long> valuesList
//            , Pageable pageable);

//    @Query(value = "select p from productCharacteristic p where p.id LIKE:name")
//    public ProductCharacteristic find(@Param("name") Long id);

   // @Query(value = "select p from productCharacteristic p WHERE p.id LIKE:name")
//     ProductSpecsValue findProductCharacteristicById(Long id);












}
