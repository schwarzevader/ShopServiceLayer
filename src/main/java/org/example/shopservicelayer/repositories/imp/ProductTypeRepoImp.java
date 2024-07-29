package org.example.shopservicelayer.repositories.imp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.shopservicelayer.entity.ProductCategory;
import org.example.shopservicelayer.entity.ProductSpecName;
import org.example.shopservicelayer.entity.ProductSpecsValue;
import org.example.shopservicelayer.entity.ProductType;
import org.example.shopservicelayer.util.categoryTreeUtil.ClassId;
import org.example.shopservicelayer.util.categoryTreeUtil.EntityGraphBuilder;
import org.example.shopservicelayer.util.categoryTreeUtil.EntityVisitor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProductTypeRepoImp {


    @PersistenceContext
    private EntityManager entityManager;

    public List<ProductType> getProductType(List<Long> id) {

        List<ProductSpecsValue> productSpecsValues = this.entityManager.createQuery(
                        "select sv " +
                                "from productSpecValue sv " +
                                "inner join fetch sv.productSpecName sn " +
                                "inner join fetch sn.productCategory pc " +
                                "inner join fetch pc.productType pt " +
                                "where pt.id in :id "
                ).setParameter("id",id)
                .getResultList();

//        return transformFromValueToCategory(productSpecsValues,id);
        return  null;
    }


    public ProductType getProductType(Long id) {

        List<ProductSpecsValue> productSpecsValues = this.entityManager.createQuery(
                        "select sv " +
                                "from productSpecValue sv " +
                                "inner join fetch sv.productSpecName sn " +
                                "inner join fetch sn.productCategory pc " +
                                "inner join fetch pc.productType pt " +
                                "where pt.id = :id "
                ).setParameter("id",id)
                .getResultList();

        return transformFromValueToCategory(productSpecsValues,id);
    }


    private  ProductType transformFromValueToCategory(List<ProductSpecsValue> productSpecsValues , Long productTypeId){
        EntityGraphBuilder entityGraphBuilder = new EntityGraphBuilder(
                new EntityVisitor[] {
                        ProductSpecsValue.ENTITY_VISITOR,
                        ProductSpecName.ENTITY_VISITOR,
                        ProductCategory.ENTITY_VISITOR,
                        ProductType.ENTITY_VISITOR
                }
        ).build(productSpecsValues);

        ClassId<ProductType> productTypeClassId = new ClassId<ProductType>(
                ProductType.class,
                productTypeId
        );

        return  entityGraphBuilder.getEntityContext().getObject(
                productTypeClassId
        );
    }
}
