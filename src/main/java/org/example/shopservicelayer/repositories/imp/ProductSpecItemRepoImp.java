

package org.example.shopservicelayer.repositories.imp;


import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;


import org.example.shopservicelayer.dto.*;
import org.example.shopservicelayer.entity.Product;
import org.example.shopservicelayer.entity.ProductSpecsValue;
import org.example.shopservicelayer.someResponse.ProductPaginationResp;
import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ProductSpecItemRepoImp {
        @PersistenceContext
    private EntityManager entityManager;

//    private Long id;
//    private String name;
//    private String description;
//    private double price;
//    private double rating;
//    private double shadowRating;

    private List<SpecsItemsDTO> specItemList= new ArrayList<>();



    public Map<SpecKey, List<SpecValue>> getProductSpecsNamesDTOMap(Long categoryId , List<Long> idList) {

//        EntityGraph<ProductSpecName> graph = entityManager.createEntityGraph(ProductSpecName.class);
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("ProductSpecName.productSpecValues");

        Map<SpecKey, List<SpecValue>> specsListMap = new HashMap<>();
        entityManager.createQuery(
                        "select  pSN.id ," +
                        "pSN.name ," +
                        "pSV.value ," +
                        "pSV.id " +
                        "from product_spec_name pSN " +
                        "join pSN.productSpecValues pSV " +
//                        "join pSV.ListSpecValue " +
                        "where  pSV.id  in :value " +
                        "group by pSV.id " +
                        "having count(distinct pSV.id) = :valueCount")
                .setParameter("value", idList)
                .setParameter("valueCount", idList.size())
//                .setHint("javax.persistence.fetchgraph", entityGraph)
                .unwrap(Query.class)
                .setTupleTransformer((tuples, aliases) -> {
                    SpecKey tempSpec = new SpecKey((long) tuples[0], (String) tuples[1]);
                    if (specsListMap.containsKey(tempSpec)) {
                        specsListMap.get(tempSpec).add(new SpecValue((String) tuples[2], (Long) tuples[3], (String) tuples[1]));
                    } else {
                        SpecKey prodSpecNameDTO = new SpecKey((long) tuples[0], (String) tuples[1]);
                        SpecValue specValue = new SpecValue((String) tuples[2], (Long) tuples[3], (String) tuples[1]);
                        List<SpecValue> list = new ArrayList<>();
                        list.add(specValue);
                        specsListMap.put(prodSpecNameDTO, list);
                    }

                    return null;
                })
                .getResultList();

        return specsListMap;
    }




    public List<Object> getProductsAndSpecValuesBySpecValuesId(List<Long> idList ,Pageable page){
//        (value = "SELECT new org.example.jpatest.dto.ProductDTO(" +
//                                                               "p.id, p.name, p.description, p.price, p.rating, p.shadowRating) " +
//                                                               "FROM productSpecItem psi " +
//                                                               "JOIN psi.product p " +
//                                                               "JOIN psi.productSpecsValue v " +
//                                                               "WHERE v.valueId IN :values " +
//                                                               "GROUP BY p.id " +
//                                                               "HAVING COUNT(DISTINCT v.valueId) = :valuesCount ")
        List<ProductDTO> someList= entityManager.createQuery("select " +
                                                         "p.id " +
                                                         ",p.name " +
                                                         ",p.description" +
                                                         ",p.price" +
                                                         ",p.rating" +
                                                         ",p.shadowRating " +
                                                         "from productSpecItem psi " +
                                                         "join psi.product p " +
                                                         "join psi.productSpecsValue psv " +
                                                         " where psv.id in :values " +
                                                         "group by p.id " +
                                                         "having count(distinct psv.id) = :valueCount" +

                                                         "")
                .unwrap(Query.class)
                .setTupleTransformer((tuples,alias)->{

                    return null;
                })
                .setParameter("values",idList)
                .setParameter("valuesCount",idList.size())
                .getResultList();

        return null;
    }

    public ProductPaginationResp getProductDTO(List <Long> valueIDList, Pageable page) {


        ProductPaginationResp productPaginationResponse = new ProductPaginationResp();
//        EntityGraph<ProductSpecName> graph = entityManager.createEntityGraph(ProductSpecName.class);
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("ProductSpecName.productSpecValues");

        List<ProdDTO> prodList = new ArrayList<>();
//        Query q = (Query)
//               .setFirstResult(1).setMaxResults(5)
//                .getResultList();

//     productPaginationResponse.setTotalPages(q.getResultList().size());

//        productPaginationResponse.setTotalElements(entityManager.createQuery(
////       return entityManager.createQuery(
//                        "select count (distinct p.id )" +
//                        "from productSpecItem psi " +
//                        "join psi.product p  " +
//                        "join psi.productSpecsValue v  " +
//                        "where v.id in: values ")
//                .setParameter("values", valueIDList)
//                .unwrap(Query.class).getResultList().size());
//

     productPaginationResponse.setTotalElements((Long) entityManager.createQuery(
//       return entityManager.createQuery(
                     "select count (distinct p.id )" +
                     "from productSpecItem psi " +
                     "join psi.product p  " +
                     "join psi.productSpecsValue v  " +
                     "where v.id in: values ")
             .setParameter("values", valueIDList)
             .getSingleResult());




     int totalProd,pageS,result;

     productPaginationResponse.setTotalPages(totalPages((int) productPaginationResponse.getTotalElements(),page.getPageSize()));
     productPaginationResponse.setProducts(
             entityManager.createQuery(
                     "select  p.id ," +
                     "p.name ," +
                     "p.description ," +
                     "p.price ," +
                     "p.rating, " +
                     "p.shadowRating," +
                     "psi.id," +
                     "psi.sizeQuantity " +
                     "from productSpecItem psi " +
                     "join psi.product p  " +
                     "join psi.productSpecsValue v  " +
                     "where v.id in: values ")
//                     "and psi.sizeQuantity >:q ")
//                        "where pC.id =: value")
             .setParameter("values", valueIDList)
//                     .setParameter("q",0)
//                .setHint("javax.persistence.fetchgraph", entityGraph)
             .unwrap(Query.class)
             .setTupleTransformer((tuples, aliases) -> {

//                    System.out.println("typle 7"+tuples[7]);
//                    prodList.add(new ProdDTO((Long)tuples[0],(String) tuples[1],(String) tuples[2],(Double) tuples[3],
//                            (Double) tuples[4],(Double) tuples[5],List.of(new SpecsItemsDTO((Long)tuples[6],(Long)tuples[7]))));
//                    return null;

                 return new ProdDTO((Long)tuples[0],(String) tuples[1],(String) tuples[2],(double) tuples[3],
                         (double) tuples[4],(Double) tuples[5],List.of(new SpecsItemsDTO((Long)tuples[6],(Long)tuples[7])));

             })
             .setFirstResult(page.getPageNumber())
             .setMaxResults(page.getPageSize())
//             .getResultList());
             .getResultList().stream().distinct().toList());

        return  productPaginationResponse;
    }

    public ProductPaginationResp getProductsDTO(List <Long> valueIDList, Pageable page) {


        ProductPaginationResp productPaginationResponse = new ProductPaginationResp();
//        EntityGraph<ProductSpecName> graph = entityManager.createEntityGraph(ProductSpecName.class);
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("ProductSpecName.productSpecValues");

        List<ProdDTO> prodList = new ArrayList<>();
//        Query q = (Query)
//               .setFirstResult(1).setMaxResults(5)
//                .getResultList();

//     productPaginationResponse.setTotalPages(q.getResultList().size());

//        productPaginationResponse.setTotalElements(entityManager.createQuery(
////       return entityManager.createQuery(
//                        "select count (distinct p.id )" +
//                        "from productSpecItem psi " +
//                        "join psi.product p  " +
//                        "join psi.productSpecsValue v  " +
//                        "where v.id in: values ")
//                .setParameter("values", valueIDList)
//                .unwrap(Query.class).getResultList().size());
//

        productPaginationResponse.setTotalElements((Long) entityManager.createQuery(
//       return entityManager.createQuery(
                        "select count (distinct p.id )" +
                        "from productSpecItem psi " +
                        "join psi.product p  " +
                        "join psi.productSpecsValue v  " +
                        "where v.id in: values ")
                .setParameter("values", valueIDList)
                .getSingleResult());




        int totalProd,pageS,result;

        productPaginationResponse.setTotalPages(totalPages((int) productPaginationResponse.getTotalElements(),page.getPageSize()));
        productPaginationResponse.setProducts(
                entityManager.createQuery(
                                "select  p.id ," +
                                "p.name ," +
                                "p.description ," +
                                "p.price ," +
                                "p.rating, " +
                                "p.shadowRating," +
                                "psi.id," +
                                "psi.sizeQuantity " +
                                "from productSpecItem psi " +
                                "join psi.product p  " +
                                "join psi.productSpecsValue v  " +
                                "where v.id in: values ")
//                     "and psi.sizeQuantity >:q ")
//                        "where pC.id =: value")
                        .setParameter("values", valueIDList)
//                     .setParameter("q",0)
//                .setHint("javax.persistence.fetchgraph", entityGraph)
                        .unwrap(Query.class)
                        .setTupleTransformer((tuples, aliases) -> {

//                    System.out.println("typle 7"+tuples[7]);
//                    prodList.add(new ProdDTO((Long)tuples[0],(String) tuples[1],(String) tuples[2],(Double) tuples[3],
//                            (Double) tuples[4],(Double) tuples[5],List.of(new SpecsItemsDTO((Long)tuples[6],(Long)tuples[7]))));
//                    return null;

                            return new ProdDTO((Long)tuples[0],(String) tuples[1],(String) tuples[2],(double) tuples[3],
                                    (double) tuples[4],(Double) tuples[5],List.of(new SpecsItemsDTO((Long)tuples[6],(Long)tuples[7])));

                        })
                        .setFirstResult(page.getPageNumber())
                        .setMaxResults(page.getPageSize())
//             .getResultList());
                        .getResultList().stream().distinct().toList());

        return  productPaginationResponse;
    }




//    entityManager.createQuery(
//            "select distinct pSN.id, pSN.name, pSV.value, pSV.id " +
//            "from product_spec_name  pSN " +
//            "left join   pSN.productSpecValues pSV on pSN.id = pSV.productSpecName.id "+
//            "left join  pSN.productCategory pC on pSN.productCategory.id=pC.id " +
//            "where pC.id = :value")
//
//            .setParameter("value", categoryId)
//                .unwrap(Query.class)
//                .setResultTransformer((tuples, aliases) -> {
//        Specs prodSpecNameDTO = new Specs();
//        prodSpecNameDTO.setId((Long) tuples[0]);
//        prodSpecNameDTO.setName((String) tuples[1]);
//        prodSpecNameDTO.getProductSpecValues().add(new SpecValue((String) tuples[2], (Long) tuples[3], (String) tuples[1]));
//        return prodSpecNameDTO;
//    })
//            .getResultList();

        public void delete(Long productID,Long specValueID){

            try {
                entityManager.getTransaction().begin();
                Product product = entityManager.getReference(Product.class,productID);
                ProductSpecsValue productSpecsValue = entityManager.find(ProductSpecsValue.class,specValueID);
                product.removeSpecValue(productSpecsValue);
                entityManager.persist(product);
                entityManager.getTransaction().commit();
            }finally {
                entityManager.close();
            }
        }



        private int totalPages(int totalElements, int pageSize){
//        int result=(totalElements/pageSize);
//        if (result*pageSize<totalElements){
//            return result+1;
//        }
//        return result;
        if (totalElements%pageSize==0){
            return totalElements/pageSize;
        }else {
            return totalElements/pageSize+1;
        }
        }
}
